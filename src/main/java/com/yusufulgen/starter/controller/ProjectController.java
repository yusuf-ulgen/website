package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Project;
import com.yusufulgen.starter.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") //  React'in bu API'ye erişmesine izin verir.
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // Veritabanındaki tüm projeleri React'e gönderir
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // React'ten gelen yeni proje verisini veritabanına kaydeder
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // ID'si verilen bir projeyi veritabanından siler
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}