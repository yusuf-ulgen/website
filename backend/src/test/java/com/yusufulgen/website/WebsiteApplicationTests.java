package com.yusufulgen.website;

import com.yusufulgen.starter.Starter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Bu test sınıfı orijinal paket ismiyle korundu.
// Main uygulama (Starter.class) açıkça belirtildi böylece @SpringBootConfiguration bulunabilir.
@SpringBootTest(classes = Starter.class)
class WebsiteApplicationTests {

    @Test
    void contextLoads() {
        // Spring Application Context'in başarıyla yüklendiğini doğrular
    }

}
