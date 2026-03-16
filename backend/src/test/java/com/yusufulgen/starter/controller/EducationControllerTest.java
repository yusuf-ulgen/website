package com.yusufulgen.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.filter.JwtAuthenticationFilter;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.EducationService;
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

@WebMvcTest(controllers = EducationController.class)
@AutoConfigureMockMvc(addFilters = false)
class EducationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EducationService educationService;

    @MockitoBean
    private AuditLogService auditLogService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getAllEducations_Success() throws Exception {
        Education ed1 = new Education();
        ed1.setId(1L);
        ed1.setSchoolName("University A");

        when(educationService.getAllEducations()).thenReturn(Arrays.asList(ed1));

        mockMvc.perform(get("/api/v1/educations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].schoolName").value("University A"));

        verify(educationService, times(1)).getAllEducations();
    }

    @SuppressWarnings("null")
    @Test
    void createEducation_Success() throws Exception {
        Education newEd = new Education();
        newEd.setSchoolName("New University");

        Education savedEd = new Education();
        savedEd.setId(2L);
        savedEd.setSchoolName("New University");

        when(educationService.saveEducation(any(Education.class))).thenReturn(savedEd);

        mockMvc.perform(post("/api/v1/educations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(2))
                .andExpect(jsonPath("$.data.schoolName").value("New University"));

        verify(educationService, times(1)).saveEducation(any(Education.class));
        verify(auditLogService, times(1)).log(eq("EDUCATION_CREATED"), anyString());
    }

    @Test
    void deleteEducation_Success() throws Exception {
        Long id = 3L;
        doNothing().when(educationService).deleteEducation(id);

        mockMvc.perform(delete("/api/v1/educations/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Eğitim bilgisi başarıyla silindi."));

        verify(educationService, times(1)).deleteEducation(id);
        verify(auditLogService, times(1)).log(eq("EDUCATION_DELETED"), anyString());
    }
}
