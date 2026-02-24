package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Message;
import com.yusufulgen.starter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message saveMessage(Message message) {
        // JavaMailSender mail atma kodunu veritabanına kaydetmeden önce ekleyeceğiz
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}