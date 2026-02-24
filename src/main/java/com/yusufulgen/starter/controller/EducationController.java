package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/educations")
@CrossOrigin(origins = "*")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping
    public List<Education> getAllEducations() {
        return educationService.getAllEducations();
    }

    @PostMapping
    public Education createEducation(@RequestBody Education education) {
        return educationService.saveEducation(education);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
    }
}