package com.yusufulgen.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<com.yusufulgen.starter.entity.AuditLog, Long> {
    
}