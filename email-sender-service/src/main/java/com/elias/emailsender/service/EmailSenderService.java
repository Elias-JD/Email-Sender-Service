package com.elias.emailsender.service;

import com.elias.emailsender.model.dto.EmailFileRequest;
import com.elias.emailsender.model.dto.EmailRequest;
import com.elias.emailsender.model.dto.WelcomeRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class EmailSenderService implements IEmailSenderService {

    @Value("${spring.mail.username}")
    private String emailAccount;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailSenderService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailRequest request) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(this.emailAccount);
        mailMessage.setTo(request.toUser());
        mailMessage.setSubject(request.subject());
        mailMessage.setText(request.message());


        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendEmailAndFile(EmailFileRequest request) {


        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage, true,StandardCharsets.UTF_8.name());

            helper.setFrom(this.emailAccount);
            helper.setTo(request.toUser());
            helper.setSubject(request.subject());
            helper.setText(request.message());
            helper.addAttachment(Objects.requireNonNull(request.file().getOriginalFilename()), request.file());
            javaMailSender.send(mimeMessage);


        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email with attachment", e);
        }

    }

    @Override
    public void sendHtmlEmail(WelcomeRequest request) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            Context context = new Context();
            context.setVariable("username", request.username());
            context.setVariable("messageContent", request.message());

            String htmlContent = templateEngine.process("email-template", context);

            helper.setFrom(emailAccount);
            helper.setTo(request.toUser());
            helper.setSubject(request.subject());
            helper.setText(htmlContent, true);

            ClassPathResource image = new ClassPathResource("static/images/logo.png");
            helper.addInline("logo-image", image);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Error sending HTML email", e);
        }
    }

}
