package com.yusufulgen.starter.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageResponse {
    
    private Long id;
    private String senderName;
    private String senderEmail;
    private String subject;
    private String content;
    private LocalDateTime sentAt;
}