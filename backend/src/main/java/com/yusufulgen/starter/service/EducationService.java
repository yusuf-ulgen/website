package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public List<Education> getAllEducations() {
        return educationRepository.findAll();
    }

    @SuppressWarnings("null")
    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    @SuppressWarnings("null")
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}