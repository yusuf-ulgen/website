package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.entity.Profile;
import com.yusufulgen.starter.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<Profile> getProfile() {
        return profileService.getProfile();
    }

    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile) {
        return profileService.saveProfile(profile);
    }
}