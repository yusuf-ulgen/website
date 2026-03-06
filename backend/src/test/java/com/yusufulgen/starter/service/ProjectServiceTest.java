package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Project;
import com.yusufulgen.starter.repository.ProjectRepository;
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
class ProjectServiceTest {

    @Mock
    private AuditLogService auditLogService;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void getAllProjects_Success() {
        // Arrange
        Project p1 = new Project();
        p1.setTitle("Title 1");
        when(projectRepository.findAll()).thenReturn(Arrays.asList(p1));

        // Act
        List<Project> result = projectService.getAllProjects();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Title 1", result.get(0).getTitle());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void saveProject_Success() {
        // Arrange
        Project p = new Project();
        p.setTitle("New Tech");
        
        Project saved = new Project();
        saved.setId(1L);
        saved.setTitle("New Tech");

        when(projectRepository.save(any(Project.class))).thenReturn(saved);

        // Act
        Project result = projectService.saveProject(p);

        // Assert
        assertNotNull(result.getId());
        assertEquals("New Tech", result.getTitle());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void deleteProject_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(projectRepository).deleteById(id);
        doNothing().when(auditLogService).log(anyString(), anyString());

        // Act
        projectService.deleteProject(id);

        // Assert
        verify(projectRepository, times(1)).deleteById(id);
        verify(auditLogService, times(1)).log(eq("DELETE_PROJECT"), anyString());
    }
}
