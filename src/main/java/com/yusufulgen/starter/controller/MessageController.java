package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Message;
import com.yusufulgen.starter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // Veritabanındaki tüm mesajları listeler
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Sitedeki iletişim formundan gelen yeni mesajı kaydeder
    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    // Okuduğun veya silmek istediğin bir mesajı ID'sine göre siler
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
    }
}