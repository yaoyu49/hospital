package com.example.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    // Additional web configuration can be added here if needed
    // For now, CORS is handled in CorsConfig and Security is handled in SecurityConfig
}