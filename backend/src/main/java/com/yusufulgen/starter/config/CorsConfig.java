package com.yusufulgen.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Güvenlik izinleri
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173"); // Sadece senin React uygulaman
        config.addAllowedHeader("*"); // Tüm başlıklara izin ver (Authorization vb.)
        config.addAllowedMethod("*"); // GET, POST, PUT, DELETE, OPTIONS hepsine izin ver
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}