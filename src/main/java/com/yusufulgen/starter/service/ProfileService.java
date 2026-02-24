package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Profile;
import com.yusufulgen.starter.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getProfile() {
        return profileRepository.findAll();
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}