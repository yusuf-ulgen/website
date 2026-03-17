package com.yusufulgen.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String mailFrom;

    @Async
    public void sendNotification(String senderName, String senderEmail, String subject, String content) {
        System.out.println("DEBUG [" + Thread.currentThread().getName() + "]: E-posta gönderimi başlatılıyor... Alıcı: ysfulgen142@gmail.com | Kimden: " + mailFrom);
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(mailFrom);
        message.setTo("ysfulgen142@gmail.com"); // Adminin e-posta adresi
        message.setSubject("Sitenizden Yeni Mesaj: " + subject);
        message.setText("Gönderen: " + senderName + "\n" +
                        "E-Posta: " + senderEmail + "\n\n" +
                        "Mesaj İçeriği:\n" + content);

        try {
            mailSender.send(message);
            System.out.println("E-posta başarıyla gönderildi!");
        } catch (Exception e) {
            System.err.println("KRİTİK HATA: E-posta gönderilemedi!");
            System.err.println("Hata Mesajı: " + e.getMessage());
            e.printStackTrace();
        }
    }
}