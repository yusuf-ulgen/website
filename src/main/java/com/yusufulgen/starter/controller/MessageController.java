package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.request.MessageRequest;
import com.yusufulgen.starter.dto.response.MessageResponse;
import com.yusufulgen.starter.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllMessages();
    }

    // Parametre olarak Entity (Message) değil, Kurye (MessageRequest) alıyoruz!
    @PostMapping
    public MessageResponse sendMessage(@Valid @RequestBody MessageRequest request) {
        return messageService.saveMessage(request);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}