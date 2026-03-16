package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.response.ApiResponse;
import com.yusufulgen.starter.entity.Project;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin(origins = {"http://localhost:5173", "https://www.yusufulgen.com", "https://yusufulgen.com"})
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        return ResponseEntity.ok(ApiResponse.success(projectService.getAllProjects()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Project>> createProject(@RequestBody Project project) {
        Project savedProject = projectService.saveProject(project);
        auditLogService.log("PROJECT_CREATED", "Yeni proje eklendi: " + savedProject.getTitle());
        return ResponseEntity.ok(ApiResponse.success(savedProject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> updateProject(@PathVariable Long id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        auditLogService.log("PROJECT_UPDATED", "Proje güncellendi: " + updatedProject.getTitle());
        return ResponseEntity.ok(ApiResponse.success(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        auditLogService.log("PROJECT_DELETED", "Proje silindi, ID: " + id);
        return ResponseEntity.ok(ApiResponse.success("Proje başarıyla silindi."));
    }
}