package com.elias.emailsender.service;

import com.elias.emailsender.model.dto.EmailFileRequest;
import com.elias.emailsender.model.dto.EmailRequest;
import com.elias.emailsender.model.dto.WelcomeRequest;


public interface IEmailSenderService {

    void sendEmail(EmailRequest request);

    void sendEmailAndFile(EmailFileRequest request);

    void sendHtmlEmail(WelcomeRequest request);
}
