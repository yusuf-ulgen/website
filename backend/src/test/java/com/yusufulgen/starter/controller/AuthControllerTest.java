package com.yusufulgen.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufulgen.starter.dto.request.LoginRequest;
import com.yusufulgen.starter.filter.JwtAuthenticationFilter;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockitoBean
        private JwtUtil jwtUtil;

        @MockitoBean
        private AuthenticationManager authenticationManager;

        @MockitoBean
        private AuditLogService auditLogService;

        @MockitoBean
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @SuppressWarnings("null")
        @Test
        void login_Success() throws Exception {
                // Arrange
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername("admin");
                loginRequest.setPassword("admin123");
                String fakeToken = "fake-jwt-token";

                when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                                .thenReturn(null);
                when(jwtUtil.generateToken("admin")).thenReturn(fakeToken);

                // Act & Assert
                mockMvc.perform(post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data").value(fakeToken));

                verify(auditLogService, times(1)).log(eq("LOGIN_SUCCESS"), anyString());
        }

        @SuppressWarnings("null")
        @Test
        void login_Failed_BadCredentials() throws Exception {
                // Arrange
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername("admin");
                loginRequest.setPassword("wrongpassword");

                when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                                .thenThrow(new BadCredentialsException("Bad credentials"));

                // Act & Assert
                mockMvc.perform(post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest)))
                                .andExpect(status().isUnauthorized())
                                .andExpect(jsonPath("$.success").value(false))
                                .andExpect(jsonPath("$.errors[0]").value("Hatalı kullanıcı adı veya şifre!"));

                verify(auditLogService, times(1)).log(eq("LOGIN_FAILED"), anyString());
        }
}
