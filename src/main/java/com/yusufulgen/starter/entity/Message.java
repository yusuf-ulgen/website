package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName; // Gönderenin adı

    private String senderEmail; // Gönderenin e-posta adresi

    private String subject; // Mesajın konusu

    @Column(columnDefinition = "TEXT")
    private String content; // Mesajın içeriği

    @CreationTimestamp
    private LocalDateTime sentAt; // Mesajın gönderildiği tarih ve saat
}