package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Profile;
import com.yusufulgen.starter.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    // Profil bilgilerini getirir
    @GetMapping
    public List<Profile> getProfile() {
        return profileRepository.findAll();
    }

    // Yeni profil bilgisi ekler veya mevcut olanı günceller
    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }
}