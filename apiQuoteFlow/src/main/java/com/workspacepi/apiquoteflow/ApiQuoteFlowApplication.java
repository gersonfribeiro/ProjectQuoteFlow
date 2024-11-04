package com.workspacepi.apiquoteflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration  // Indica que esta classe contém definições de configuração para o Spring
@SpringBootApplication  // Marca a classe como uma aplicação Spring Boot
public class ApiQuoteFlowApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ApiQuoteFlowApplication.class, args);  // Inicia a aplicação Spring Boot
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Habilita o CORS para todos os endpoints
                .allowedOrigins("http://localhost:4200")  // Permite requisições apenas do frontend rodando em localhost:4200
                .allowedMethods("*")  // Permite todos os métodos HTTP (GET, POST, etc.)
                .allowedHeaders("*") // Permite todos os cabeçalhos nas requisições
                .allowCredentials(true);
    }
}
