package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.AuditLog;
import com.yusufulgen.starter.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository repository;

    public void log(String action, String details) {
        // Mevcut giriş yapmış kullanıcının adını JWT üzerinden çeker
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);
        auditLog.setAction(action);
        auditLog.setDetails(details);
        auditLog.setTimestamp(LocalDateTime.now());
        
        repository.save(auditLog);
    }
}