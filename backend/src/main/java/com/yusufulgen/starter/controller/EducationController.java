package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.response.ApiResponse;
import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.service.EducationService;
import com.yusufulgen.starter.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/educations")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Education>>> getAllEducations() {
        return ResponseEntity.ok(ApiResponse.success(educationService.getAllEducations()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Education>> createEducation(@RequestBody Education education) {
        Education savedEducation = educationService.saveEducation(education);
        auditLogService.log("EDUCATION_CREATED", "Eğitim bilgisi eklendi: " + savedEducation.getSchoolName());
        return ResponseEntity.ok(ApiResponse.success(savedEducation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Education>> updateEducation(@PathVariable Long id,
            @RequestBody Education education) {
        Education updatedEducation = educationService.updateEducation(id, education);
        auditLogService.log("EDUCATION_UPDATED", "Eğitim bilgisi güncellendi: " + updatedEducation.getSchoolName());
        return ResponseEntity.ok(ApiResponse.success(updatedEducation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        auditLogService.log("EDUCATION_DELETED", "Eğitim kaydı silindi, ID: " + id);
        return ResponseEntity.ok(ApiResponse.success("Eğitim bilgisi başarıyla silindi."));
    }
}