package com.yusufulgen.starter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String mailFrom;

    @Async
    public void sendNotification(String senderName, String senderEmail, String subject, String content) {
        logger.info("E-posta gönderimi başlatılıyor... Alıcı: ysfulgen142@gmail.com | Gönderen: {}", mailFrom);
        System.out.println(">>> EMAIL-THREAD: İşlem başladı. Alıcı: ysfulgen142@gmail.com | Gönderen: " + mailFrom);
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(mailFrom);
        message.setTo("ysfulgen142@gmail.com"); // Adminin e-posta adresi
        message.setSubject("Sitenizden Yeni Mesaj: " + subject);
        message.setText("Gönderen: " + senderName + "\n" +
                        "E-Posta: " + senderEmail + "\n\n" +
                        "Mesaj İçeriği:\n" + content);

        try {
            mailSender.send(message);
            logger.info("E-posta başarıyla gönderildi!");
            System.out.println(">>> EMAIL-THREAD: E-posta GÖNDERİLDİ.");
        } catch (Exception e) {
            logger.error("KRİTİK HATA: E-posta gönderilemedi! Hata: {}", e.getMessage(), e);
            System.err.println(">>> EMAIL-THREAD: KRİTİK HATA! " + e.getMessage());
            e.printStackTrace();
        }
    }
}