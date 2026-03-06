package com.yusufulgen.starter.service;

import com.yusufulgen.starter.dto.request.MessageRequest;
import com.yusufulgen.starter.dto.response.MessageResponse;
import com.yusufulgen.starter.entity.Message;
import com.yusufulgen.starter.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private MessageService messageService;

    @Test
    void getAllMessages_Success() {
        // Arrange
        Message m = new Message();
        m.setId(1L);
        m.setSenderName("John Doe");
        m.setSenderEmail("john@doe.com");
        
        when(messageRepository.findAll()).thenReturn(Arrays.asList(m));

        // Act
        List<MessageResponse> result = messageService.getAllMessages();

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getSenderName());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void saveMessage_Success() {
        // Arrange
        MessageRequest request = new MessageRequest();
        request.setSenderName("Jane");
        request.setSenderEmail("jane@doe.com");
        request.setSubject("Hello");
        request.setContent("Test Message");

        Message savedMessage = new Message();
        savedMessage.setId(5L);
        savedMessage.setSenderName("Jane");
        savedMessage.setSenderEmail("jane@doe.com");
        savedMessage.setSubject("Hello");
        savedMessage.setContent("Test Message");

        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);
        // mail atması bekleniyor ama sadece metodu çağırması bizim için yeterli ve mocklandığı için gerçek mail atılmaz
        doNothing().when(emailService).sendNotification(anyString(), anyString(), anyString(), anyString());

        // Act
        MessageResponse result = messageService.saveMessage(request);

        // Assert
        assertEquals(5L, result.getId());
        assertEquals("Jane", result.getSenderName());
        verify(messageRepository, times(1)).save(any(Message.class));
        verify(emailService, times(1)).sendNotification(eq("Jane"), eq("jane@doe.com"), eq("Hello"), eq("Test Message"));
    }

    @Test
    void deleteMessage_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(messageRepository).deleteById(id);

        // Act
        messageService.deleteMessage(id);

        // Assert
        verify(messageRepository, times(1)).deleteById(id);
    }
}
