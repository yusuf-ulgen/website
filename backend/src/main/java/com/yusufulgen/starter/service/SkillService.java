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
        // Eğer yeni bir kayıt ise (id null) veya mevcut sırası değişmişse kontrol et
        if (skill.getId() == null) {
            if (skillRepository.existsByCategoryAndOrderIndex(skill.getCategory(), skill.getOrderIndex())) {
                throw new RuntimeException("Bu kategoride bu sıra numarasıyla bir yetenek zaten mevcut!");
            }
        } else {
            // Güncelleme durumunda, başkası bu numarayı kullanıyor mu diye bak (Id çakışması hariç)
            // Not: Basitlik adına burada exists kontrolü entity'nin kendi Id'sini hariç tutacak şekilde repo'da genişletilebilir.
            // Fakat veritabanı seviyesindeki UniqueConstraint zaten kesin çözümü sağlar.
        }
        return skillRepository.save(skill);
    }

    @SuppressWarnings("null")
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}