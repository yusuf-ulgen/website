package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "educations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolName; // Okul Adı (Örn: "X Üniversitesi")

    private String department; // Bölüm (Örn: "Yazılım Mühendisliği")

    private String startDate; // Başlangıç (Örn: "2019" veya "Eylül 2019")

    private String endDate; // Bitiş (Örn: "2023" veya "Devam Ediyor")

    @Column(columnDefinition = "TEXT")
    private String description; // Açıklama veya başarılar (İsteğe bağlı)
}