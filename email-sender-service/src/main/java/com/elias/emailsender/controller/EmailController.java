package com.elias.emailsender.controller;

import com.elias.emailsender.model.dto.EmailFileRequest;
import com.elias.emailsender.model.dto.EmailRequest;
import com.elias.emailsender.model.dto.WelcomeRequest;
import com.elias.emailsender.service.IEmailSenderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    private final IEmailSenderService emailService;

    public EmailController(IEmailSenderService emailService) {
        this.emailService = emailService;
    }


    @PostMapping()
    public ResponseEntity<Map<String, String>> sendMail(@Valid @RequestBody EmailRequest request) {

        logger.info("Sending standard email to: {}", Arrays.toString(request.toUser()));
        emailService.sendEmail(request);
        return ResponseEntity.ok(Map.of("status", "The email was sent successfully."));
    }


    @PostMapping("/attachments")
    public ResponseEntity<?> receiveRequestEmailWithFile(@Valid @ModelAttribute EmailFileRequest request) {

        logger.info("Sending email with file to: {}", Arrays.toString(request.toUser()));

        if (request.file() == null || request.file().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is required"));
        }
        emailService.sendEmailAndFile(request);

        return ResponseEntity.ok(Map.of(
                "status", "The email was sent successfully.",
                "file", Objects.requireNonNull(request.file().getOriginalFilename())
        ));
    }

    @PostMapping("/welcome")
    public ResponseEntity<Map<String, String>> sendWelcome(@Valid @RequestBody WelcomeRequest request) {
        logger.info("Sending welcome email in HTML to: {}", request.username());

        emailService.sendHtmlEmail(request);

        return ResponseEntity.ok(Map.of("status", "Email in HTML format sent successfully."));
    }
}
