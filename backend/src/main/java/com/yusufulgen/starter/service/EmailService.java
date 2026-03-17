package com.yusufulgen.starter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final RestTemplate restTemplate;

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${mail.from.email}")
    private String mailFrom;

    private final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";

    @Async
    public void sendNotification(String senderName, String senderEmail, String subject, String content) {
        System.out.println(">>> EMAIL-THREAD: Brevo API üzerinden gönderim başlatılıyor...");
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", brevoApiKey);

            // Brevo API formatına uygun JSON payload oluşturma
            Map<String, Object> body = new HashMap<>();
            
            // Gönderen bilgisi
            Map<String, String> sender = new HashMap<>();
            sender.put("name", "Web sitesi İletişim Formu");
            sender.put("email", mailFrom);
            body.put("sender", sender);

            // Alıcı bilgisi
            Map<String, String> to = new HashMap<>();
            to.put("email", mailFrom);
            body.put("to", Collections.singletonList(to));

            // İçerik
            body.put("subject", "Yeni Mesaj: " + subject);
            body.put("htmlContent", String.format(
                "<h3>Yusufulgen.com üzerinden yeni mesaj aldınız!</h3>" +
                "<p><strong>Gönderen:</strong> %s (%s)</p>" +
                "<p><strong>Konu:</strong> %s</p>" +
                "<p><strong>Mesaj:</strong></p>" +
                "<p>%s</p>",
                senderName, senderEmail, subject, content
            ));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(BREVO_API_URL, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println(">>> EMAIL-THREAD: E-posta bildirimi Brevo API ile başarıyla gönderildi.");
            } else {
                System.err.println(">>> EMAIL-THREAD: Brevo API hatası! Statu: " + response.getStatusCode());
                System.err.println(">>> EMAIL-THREAD: Yanıt: " + response.getBody());
            }

        } catch (Exception e) {
            System.err.println(">>> EMAIL-THREAD: KRİTİK HATA! Brevo API bağlantısı kurulamadı.");
            e.printStackTrace();
        }
    }
}