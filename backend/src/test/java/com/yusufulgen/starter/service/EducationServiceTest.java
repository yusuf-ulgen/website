package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Education;
import com.yusufulgen.starter.repository.EducationRepository;
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
class EducationServiceTest {

    @Mock
    private EducationRepository educationRepository;

    @InjectMocks
    private EducationService educationService;

    @Test
    void getAllEducations_Success() {
        // Arrange
        Education ed = new Education();
        ed.setId(1L);
        ed.setSchoolName("Uni 1");
        when(educationRepository.findAll()).thenReturn(Arrays.asList(ed));

        // Act
        List<Education> result = educationService.getAllEducations();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Uni 1", result.get(0).getSchoolName());
        verify(educationRepository, times(1)).findAll();
    }

    @SuppressWarnings("null")
    @Test
    void saveEducation_Success() {
        // Arrange
        Education ed = new Education();
        ed.setSchoolName("Uni");

        Education savedEd = new Education();
        savedEd.setId(2L);
        savedEd.setSchoolName("Uni");

        when(educationRepository.save(any(Education.class))).thenReturn(savedEd);

        // Act
        Education result = educationService.saveEducation(ed);

        // Assert
        assertEquals(2L, result.getId());
        assertEquals("Uni", result.getSchoolName());
        verify(educationRepository, times(1)).save(any(Education.class));
    }

    @Test
    void deleteEducation_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(educationRepository).deleteById(id);

        // Act
        educationService.deleteEducation(id);

        // Assert
        verify(educationRepository, times(1)).deleteById(id);
    }
}
