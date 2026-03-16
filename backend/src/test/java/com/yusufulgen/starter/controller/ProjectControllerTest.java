package com.yusufulgen.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufulgen.starter.entity.Project;
import com.yusufulgen.starter.filter.JwtAuthenticationFilter;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProjectService projectService;

    @MockitoBean
    private AuditLogService auditLogService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getAllProjects_Success() throws Exception {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setTitle("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setTitle("Project 2");

        List<Project> mockProjects = Arrays.asList(project1, project2);
        when(projectService.getAllProjects()).thenReturn(mockProjects);

        mockMvc.perform(get("/api/v1/projects").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].title").value("Project 1"));

        verify(projectService, times(1)).getAllProjects();
    }

    @SuppressWarnings("null")
    @Test
    void createProject_Success() throws Exception {
        Project newProject = new Project();
        newProject.setTitle("New Project");

        Project savedProject = new Project();
        savedProject.setId(10L);
        savedProject.setTitle("New Project");

        when(projectService.saveProject(any(Project.class))).thenReturn(savedProject);

        mockMvc.perform(post("/api/v1/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(10))
                .andExpect(jsonPath("$.data.title").value("New Project"));

        verify(projectService, times(1)).saveProject(any(Project.class));
        verify(auditLogService, times(1)).log(eq("PROJECT_CREATED"), anyString());
    }

    @Test
    void deleteProject_Success() throws Exception {
        Long projectId = 1L;
        doNothing().when(projectService).deleteProject(projectId);

        mockMvc.perform(delete("/api/v1/projects/{id}", projectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Proje başarıyla silindi."));

        verify(projectService, times(1)).deleteProject(projectId);
        verify(auditLogService, times(1)).log(eq("PROJECT_DELETED"), anyString());
    }
}
