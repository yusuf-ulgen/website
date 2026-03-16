package com.yusufulgen.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufulgen.starter.entity.Skill;
import com.yusufulgen.starter.filter.JwtAuthenticationFilter;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.SkillService;
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

@WebMvcTest(controllers = SkillController.class)
@AutoConfigureMockMvc(addFilters = false)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SkillService skillService;

    @MockitoBean
    private AuditLogService auditLogService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getAllSkills_Success() throws Exception {
        Skill s1 = new Skill();
        s1.setId(1L);
        s1.setName("Java");

        when(skillService.getAllSkills()).thenReturn(Arrays.asList(s1));

        mockMvc.perform(get("/api/v1/skills").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Java"));

        verify(skillService, times(1)).getAllSkills();
    }

    @SuppressWarnings("null")
    @Test
    void createSkill_Success() throws Exception {
        Skill s = new Skill();
        s.setName("React");

        Skill savedS = new Skill();
        savedS.setId(2L);
        savedS.setName("React");

        when(skillService.saveSkill(any(Skill.class))).thenReturn(savedS);

        mockMvc.perform(post("/api/v1/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(2))
                .andExpect(jsonPath("$.data.name").value("React"));

        verify(skillService, times(1)).saveSkill(any(Skill.class));
        verify(auditLogService, times(1)).log(eq("SKILL_CREATED"), anyString());
    }

    @Test
    void deleteSkill_Success() throws Exception {
        Long id = 3L;
        doNothing().when(skillService).deleteSkill(id);

        mockMvc.perform(delete("/api/v1/skills/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Yetenek başarıyla silindi."));

        verify(skillService, times(1)).deleteSkill(id);
        verify(auditLogService, times(1)).log(eq("SKILL_DELETED"), anyString());
    }
}
