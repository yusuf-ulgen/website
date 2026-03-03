package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName; // Adın ve Soyadın

    private String title; // Mesleki unvanın (Örn: "Full Stack Software Developer")

    @Column(columnDefinition = "TEXT")
    private String bio; // Hakkımda

    private String skills; // Yeteneklerin (Örn: "Java, Spring Boot, React, PostgreSQL")

    // Sosyal medya ve iletişim linklerin
    private String email;
    private String githubLink;
    private String linkedinLink;
}