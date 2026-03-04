package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Skill;
import com.yusufulgen.starter.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @SuppressWarnings("null")
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @SuppressWarnings("null")
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}