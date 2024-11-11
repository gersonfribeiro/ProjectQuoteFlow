package com.workspacepi.apiquoteflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration  // Indica que esta classe contém definições de configuração para o Spring
@SpringBootApplication  // Marca a classe como uma aplicação Spring Boot
public class ApiQuoteFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiQuoteFlowApplication.class, args);  // Inicia a aplicação Spring Boot
    }
}
