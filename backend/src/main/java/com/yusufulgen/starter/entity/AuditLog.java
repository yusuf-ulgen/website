package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String action; // Örn: DELETE_PROJECT, LOGIN
    private String details;
    private LocalDateTime timestamp;
}