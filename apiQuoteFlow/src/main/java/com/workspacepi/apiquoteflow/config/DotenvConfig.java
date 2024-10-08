package com.workspacepi.apiquoteflow.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DotenvConfig {

    @PostConstruct
    public void loadDotenv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DOTENV_NAME", dotenv.get("DOTENV_NAME"));
        System.setProperty("DOTENV_URL", dotenv.get("DOTENV_URL"));
        System.setProperty("DOTENV_USERNAME", dotenv.get("DOTENV_USERNAME"));
        System.setProperty("DOTENV_PASSWORD", dotenv.get("DOTENV_PASSWORD"));

        // Opcional: Verifique se as variáveis estão sendo carregadas corretamente
        System.out.println("DOTENV_URL: " + dotenv.get("DOTENV_URL"));
    }
}


