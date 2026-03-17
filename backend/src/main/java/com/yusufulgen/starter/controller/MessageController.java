package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.request.MessageRequest;
import com.yusufulgen.starter.dto.response.ApiResponse;
import com.yusufulgen.starter.dto.response.MessageResponse;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MessageResponse>>> getAllMessages() {
        return ResponseEntity.ok(ApiResponse.success(messageService.getAllMessages()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MessageResponse>> sendMessage(@Valid @RequestBody MessageRequest request) {
        System.out.println(">>> CONTROLLER: Mesaj isteği alındı. Gönderen: " + request.getSenderEmail());
        MessageResponse response = messageService.saveMessage(request);
        System.out.println(">>> CONTROLLER: Mesaj kaydedildi, response dönülüyor.");
        
        try {
            auditLogService.log("MESSAGE_RECEIVED", "Yeni iletişim formu mesajı alındı: " + request.getSenderEmail());
        } catch (Exception e) {
            System.err.println(">>> CONTROLLER: AuditLog hatası (ihmal edilebilir): " + e.getMessage());
        }
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        auditLogService.log("MESSAGE_DELETED", "Mesaj silindi, ID: " + id);
        return ResponseEntity.ok(ApiResponse.success("Mesaj başarıyla silindi."));
    }
}