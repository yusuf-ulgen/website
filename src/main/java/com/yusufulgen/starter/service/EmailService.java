package com.yusufulgen.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(String senderName, String senderEmail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("ysfulgen142@gmail.com"); // Adminin e-posta adresi
        message.setSubject("Sitenizden Yeni Mesaj: " + subject);
        message.setText("Gönderen: " + senderName + "\n" +
                        "E-Posta: " + senderEmail + "\n\n" +
                        "Mesaj İçeriği:\n" + content);

        mailSender.send(message);
    }
}