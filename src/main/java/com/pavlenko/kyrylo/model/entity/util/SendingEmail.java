package com.pavlenko.kyrylo.model.entity.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class SendingEmail {

    private String userEmail;
    private final Logger logger = LogManager.getLogger(SendingEmail.class);

    public SendingEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void sentEmail(){
        String fromEmail = "rentingcarbot@gmail.com";
        String password = "ydrhexycajuvxbgq";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Car Renting email verification Link");
            message.setText("Verification Link...");
            message.setText("Your verification link: " +
                    "http://localhost:8080/Car_Rental_Servlet_Project_war/ActivateAccount?key1=" + userEmail);
            Transport.send(message);

        } catch (MessagingException e) {
            logger.error("Sending email error: {}", e.getMessage());
        }

    }
}
