package com.yusufulgen.starter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class StarterApplicationTests {

    @Test
    void contextLoads() {
        // Spring Application Context'in sorunsuz yüklendiğini doğrular
    }

}
