package com.yusufulgen.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufulgen.starter.entity.Profile;
import com.yusufulgen.starter.filter.JwtAuthenticationFilter;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.ProfileService;
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

@WebMvcTest(controllers = ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProfileService profileService;

    @MockitoBean
    private AuditLogService auditLogService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getProfile_Success() throws Exception {
        Profile p1 = new Profile();
        p1.setId(1L);
        p1.setFullName("John Doe");

        when(profileService.getProfile()).thenReturn(Arrays.asList(p1));

        mockMvc.perform(get("/api/v1/profile").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].fullName").value("John Doe"));

        verify(profileService, times(1)).getProfile();
    }

    @Test
    void saveProfile_Success() throws Exception {
        Profile p = new Profile();
        p.setFullName("Jane Doe");

        Profile savedP = new Profile();
        savedP.setId(1L);
        savedP.setFullName("Jane Doe");

        when(profileService.saveProfile(any(Profile.class))).thenReturn(savedP);

        mockMvc.perform(post("/api/v1/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.fullName").value("Jane Doe"));

        verify(profileService, times(1)).saveProfile(any(Profile.class));
        verify(auditLogService, times(1)).log(eq("PROFILE_UPDATED"), anyString());
    }
}
