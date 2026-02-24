package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/educations")
@CrossOrigin(origins = "*")
public class EducationController {

    @Autowired
    private EducationRepository educationRepository;

    @GetMapping
    public List<Education> getAllEducations() {
        return educationRepository.findAll();
    }

    @PostMapping
    public Education createEducation(@RequestBody Education education) {
        return educationRepository.save(education);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationRepository.deleteById(id);
    }
}