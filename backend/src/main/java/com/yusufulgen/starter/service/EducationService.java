package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.yusufulgen.starter.dto.request.EducationReorderDto;

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
    public Education updateEducation(Long id, Education updatedEducation) {
        Education existingEducation = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eğitim bilgisi bulunamadı, ID: " + id));

        existingEducation.setSchoolName(updatedEducation.getSchoolName());
        existingEducation.setDepartment(updatedEducation.getDepartment());
        existingEducation.setStartDate(updatedEducation.getStartDate());
        existingEducation.setEndDate(updatedEducation.getEndDate());
        existingEducation.setDescription(updatedEducation.getDescription());
        existingEducation.setEducationType(updatedEducation.getEducationType());
        existingEducation.setSchoolNameEn(updatedEducation.getSchoolNameEn());
        existingEducation.setDepartmentEn(updatedEducation.getDepartmentEn());
        existingEducation.setDescriptionEn(updatedEducation.getDescriptionEn());
        if (updatedEducation.getOrderIndex() != null) {
            existingEducation.setOrderIndex(updatedEducation.getOrderIndex());
        }

        return educationRepository.save(existingEducation);
    }

    @SuppressWarnings("null")
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    public void reorderEducations(List<EducationReorderDto> requestList) {
        for (EducationReorderDto request : requestList) {
            Education education = educationRepository.findById(request.getId()).orElse(null);
            if (education != null) {
                education.setOrderIndex(request.getOrderIndex());
                educationRepository.save(education);
            }
        }
    }
}