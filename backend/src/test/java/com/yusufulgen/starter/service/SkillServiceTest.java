package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Skill;
import com.yusufulgen.starter.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    void getAllSkills_Success() {
        // Arrange
        Skill s = new Skill();
        s.setId(1L);
        s.setName("React");
        when(skillRepository.findAll()).thenReturn(Arrays.asList(s));

        // Act
        List<Skill> result = skillService.getAllSkills();

        // Assert
        assertEquals(1, result.size());
        assertEquals("React", result.get(0).getName());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void saveSkill_Success() {
        // Arrange
        Skill s = new Skill();
        s.setName("Spring");

        Skill savedS = new Skill();
        savedS.setId(2L);
        savedS.setName("Spring");

        when(skillRepository.save(any(Skill.class))).thenReturn(savedS);

        // Act
        Skill result = skillService.saveSkill(s);

        // Assert
        assertEquals(2L, result.getId());
        assertEquals("Spring", result.getName());
        verify(skillRepository, times(1)).save(any(Skill.class));
    }

    @Test
    void deleteSkill_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(skillRepository).deleteById(id);

        // Act
        skillService.deleteSkill(id);

        // Assert
        verify(skillRepository, times(1)).deleteById(id);
    }
}
