package com.customer.service;


import com.customer.api.Ticket;
import com.customer.util.EmailTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public EmailTemplateBuilder emailTemplateBuilder;

    private static String subjectTemplate = "R1 CAS Support Level1 - Incident: {0} - Priority:4 ";

    public void sendSupportEmail(Ticket ticket, String comment) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setText(emailTemplateBuilder.build(ticket, comment), true);
            helper.setSubject(MessageFormat.format(subjectTemplate, ticket.getIncident().toString()));
            helper.setTo("creddy@routeone.com");
        } catch (MessagingException e) {


        }
        emailSender.send(message);
    }


}