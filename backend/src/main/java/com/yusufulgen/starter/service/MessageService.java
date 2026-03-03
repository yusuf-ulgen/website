package com.yusufulgen.starter.service;

import com.yusufulgen.starter.dto.request.MessageRequest;
import com.yusufulgen.starter.dto.response.MessageResponse;
import com.yusufulgen.starter.entity.Message;
import com.yusufulgen.starter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private EmailService emailService;

    // React'e liste gönderirken Entity'leri Response DTO'ya çeviriyoruz
    public List<MessageResponse> getAllMessages() {
        return messageRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // React'ten gelen Request DTO'yu Entity'e çevirip kaydediyoruz
    public MessageResponse saveMessage(MessageRequest request) {
        
        // 1. Kuryeden (DTO) gelenleri Veritabanı objesine aktar
        Message message = new Message();
        message.setSenderName(request.getSenderName());
        message.setSenderEmail(request.getSenderEmail());
        message.setSubject(request.getSubject());
        message.setContent(request.getContent());

        // 2. Veritabanına kaydet
        Message savedMessage = messageRepository.save(message);

        // 3. Mail at
        emailService.sendNotification(
                savedMessage.getSenderName(),
                savedMessage.getSenderEmail(),
                savedMessage.getSubject(),
                savedMessage.getContent()
        );

        // 4. Kaydedilen veriyi Response DTO'ya çevirip geri döndür
        return convertToResponse(savedMessage);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    // Çeviri (Mapping) işlemini yapan yardımcı metod
    private MessageResponse convertToResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setSenderName(message.getSenderName());
        response.setSenderEmail(message.getSenderEmail());
        response.setSubject(message.getSubject());
        response.setContent(message.getContent());
        response.setSentAt(message.getSentAt());
        return response;
    }
}