package com.policy.function.changemanagement.service.impl;

import com.policy.function.changemanagement.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

        private final JavaMailSender mailSender;

        @Value("${spring.mail.username}")
        private String fromEmail;

        public EmailServiceImpl(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        @Override
        public void sendTestEmail(String to, String subject, String content) {
            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email", e);
            }
        }
}
