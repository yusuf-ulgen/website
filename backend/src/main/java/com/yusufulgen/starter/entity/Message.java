package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "İsim alanı boş bırakılamaz!")
    @Size(min = 2, max = 50, message = "İsim en az 2, en fazla 50 karakter olmalıdır!")
    private String senderName;

    @NotBlank(message = "E-Posta alanı boş bırakılamaz!")
    @Email(message = "Lütfen geçerli bir e-posta adresi giriniz!")
    private String senderEmail;

    @NotBlank(message = "Konu boş bırakılamaz!")
    private String subject;

    @NotBlank(message = "Mesaj içeriği boş bırakılamaz!")
    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime sentAt;
}