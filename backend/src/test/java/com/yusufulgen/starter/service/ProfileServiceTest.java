package com.yusufulgen.starter.service;

import com.yusufulgen.starter.entity.Profile;
import com.yusufulgen.starter.repository.ProfileRepository;
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
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void getProfile_Success() {
        // Arrange
        Profile p = new Profile();
        p.setId(1L);
        p.setFullName("Yusuf");
        when(profileRepository.findAll()).thenReturn(Arrays.asList(p));

        // Act
        List<Profile> result = profileService.getProfile();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Yusuf", result.get(0).getFullName());
        verify(profileRepository, times(1)).findAll();
    }

    @SuppressWarnings("null")
    @Test
    void saveProfile_Success() {
        // Arrange
        Profile p = new Profile();
        p.setFullName("John");

        Profile savedP = new Profile();
        savedP.setId(2L);
        savedP.setFullName("John");

        when(profileRepository.save(any(Profile.class))).thenReturn(savedP);

        // Act
        Profile result = profileService.saveProfile(p);

        // Assert
        assertEquals(2L, result.getId());
        assertEquals("John", result.getFullName());
        verify(profileRepository, times(1)).save(any(Profile.class));
    }
}
