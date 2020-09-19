package com.petprojects.community.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailProvider{
    private static JavaMailSender mailSender;

    @Autowired
    public EmailProvider(@Qualifier("getJavaMailSender") JavaMailSender javaMailSender) {
        mailSender = javaMailSender;
    }

    public static String sendValidationCode(String to) {
        String validationCode = UUID.randomUUID().toString().replace("-", "");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Validation Code (No Reply)");

        String massageText = String.format("Validation Code is: %s", validationCode);
        message.setText(massageText);
        mailSender.send(message);

        return validationCode;
    }
}
