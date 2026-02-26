package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.response.ApiResponse;
import com.yusufulgen.starter.entity.Skill;
import com.yusufulgen.starter.service.SkillService;
import com.yusufulgen.starter.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@CrossOrigin(origins = "*")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Skill>>> getAllSkills() {
        return ResponseEntity.ok(ApiResponse.success(skillService.getAllSkills()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Skill>> createSkill(@RequestBody Skill skill) {
        Skill savedSkill = skillService.saveSkill(skill);
        auditLogService.log("SKILL_CREATED", "Yeni yetenek eklendi: " + savedSkill.getName());
        return ResponseEntity.ok(ApiResponse.success(savedSkill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        auditLogService.log("SKILL_DELETED", "Yetenek silindi, ID: " + id);
        return ResponseEntity.ok(ApiResponse.success("Yetenek başarıyla silindi."));
    }
}