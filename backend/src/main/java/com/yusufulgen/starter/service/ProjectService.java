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
    @SuppressWarnings("null")
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    // Proje güncelle
    @SuppressWarnings("null")
    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proje bulunamadı, ID: " + id));

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setTitleEn(updatedProject.getTitleEn());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setDescriptionEn(updatedProject.getDescriptionEn());
        existingProject.setTechnologies(updatedProject.getTechnologies());
        existingProject.setDuration(updatedProject.getDuration());
        existingProject.setGithubUrl(updatedProject.getGithubUrl());
        existingProject.setLiveUrl(updatedProject.getLiveUrl());

        return projectRepository.save(existingProject);
    }

    // Proje sil
    @SuppressWarnings("null")
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
        auditLogService.log("DELETE_PROJECT", "ID: " + id + " olan proje silindi.");
    }
}