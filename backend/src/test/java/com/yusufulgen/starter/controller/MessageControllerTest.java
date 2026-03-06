package com.yusufulgen.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufulgen.starter.dto.request.MessageRequest;
import com.yusufulgen.starter.dto.response.MessageResponse;
import com.yusufulgen.starter.filter.JwtAuthenticationFilter;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MessageService messageService;

    @MockitoBean
    private AuditLogService auditLogService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getAllMessages_Success() throws Exception {
        MessageResponse msg1 = new MessageResponse();
        msg1.setId(1L);
        msg1.setSenderName("John Doe");

        MessageResponse msg2 = new MessageResponse();
        msg2.setId(2L);
        msg2.setSenderName("Jane Doe");

        when(messageService.getAllMessages()).thenReturn(Arrays.asList(msg1, msg2));

        mockMvc.perform(get("/api/v1/messages").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].senderName").value("John Doe"));

        verify(messageService, times(1)).getAllMessages();
    }

    @Test
    void sendMessage_Success() throws Exception {
        MessageRequest request = new MessageRequest();
        request.setSenderName("Test User");
        request.setSenderEmail("test@test.com");
        request.setSubject("Test Subject");
        request.setContent("Test Content");

        MessageResponse response = new MessageResponse();
        response.setId(10L);
        response.setSenderName("Test User");
        response.setSenderEmail("test@test.com");

        when(messageService.saveMessage(any(MessageRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(10))
                .andExpect(jsonPath("$.data.senderName").value("Test User"));

        verify(messageService, times(1)).saveMessage(any(MessageRequest.class));
        verify(auditLogService, times(1)).log(eq("MESSAGE_RECEIVED"), anyString());
    }

    @Test
    void deleteMessage_Success() throws Exception {
        Long messageId = 5L;
        doNothing().when(messageService).deleteMessage(messageId);

        mockMvc.perform(delete("/api/v1/messages/{id}", messageId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Mesaj başarıyla silindi."));

        verify(messageService, times(1)).deleteMessage(messageId);
        verify(auditLogService, times(1)).log(eq("MESSAGE_DELETED"), anyString());
    }
}
