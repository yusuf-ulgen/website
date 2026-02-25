package com.yusufulgen.starter.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequest {

    @NotBlank(message = "İsim alanı boş bırakılamaz!")
    @Size(min = 2, max = 50, message = "İsim en az 2, en fazla 50 karakter olmalıdır!")
    private String senderName;

    @NotBlank(message = "E-Posta alanı boş bırakılamaz!")
    @Email(message = "Lütfen geçerli bir e-posta adresi giriniz!")
    private String senderEmail;

    @NotBlank(message = "Konu boş bırakılamaz!")
    private String subject;

    @NotBlank(message = "Mesaj içeriği boş bırakılamaz!")
    private String content;
}