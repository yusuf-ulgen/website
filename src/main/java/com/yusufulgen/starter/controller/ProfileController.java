package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.response.ApiResponse;
import com.yusufulgen.starter.entity.Profile;
import com.yusufulgen.starter.service.ProfileService;
import com.yusufulgen.starter.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Profile>>> getProfile() {
        return ResponseEntity.ok(ApiResponse.success(profileService.getProfile()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Profile>> saveProfile(@RequestBody Profile profile) {
        Profile savedProfile = profileService.saveProfile(profile);
        auditLogService.log("PROFILE_UPDATED", "Profil bilgileri güncellendi.");
        return ResponseEntity.ok(ApiResponse.success(savedProfile));
    }
}