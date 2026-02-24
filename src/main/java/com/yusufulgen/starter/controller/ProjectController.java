package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Project;
import com.yusufulgen.starter.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService; // Repository YERİNE artık Service'i çağırıyoruz!

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects(); // İşlemi Service'e devrettik
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project); // İşlemi Service'e devrettik
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id); // İşlemi Service'e devrettik
    }
}