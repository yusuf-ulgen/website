package com.yusufulgen.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"category", "order_index"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String category;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex = 0;

    private Integer percentage;
}