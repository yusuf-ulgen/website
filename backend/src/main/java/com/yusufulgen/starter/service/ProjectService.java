package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Project;
import com.yusufulgen.starter.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private ProjectRepository projectRepository;

    // Tüm projeleri getir
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Yeni proje kaydet
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    // Proje sil
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
        auditLogService.log("DELETE_PROJECT", "ID: " + id + " olan proje silindi.");
    }
}