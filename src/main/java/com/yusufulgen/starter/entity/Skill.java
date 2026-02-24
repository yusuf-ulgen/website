package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Yetenek adı (Örn: "Java", "Spring Boot", "React")

    private Integer percentage; // Yüzdelik seviye (Örn: 85) - Barda veya grafikte göstermek için harika olur
}