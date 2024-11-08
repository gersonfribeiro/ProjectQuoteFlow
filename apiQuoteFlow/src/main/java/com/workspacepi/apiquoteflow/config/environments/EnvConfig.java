package com.workspacepi.apiquoteflow.config.environments;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    public EnvConfig() {
        // Carregar variáveis de ambiente do arquivo .env
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DOTENV_NAME", dotenv.get("DOTENV_NAME"));
        System.setProperty("DOTENV_URL", dotenv.get("DOTENV_URL"));
        System.setProperty("DOTENV_USERNAME", dotenv.get("DOTENV_USERNAME"));
        System.setProperty("DOTENV_PASSWORD", dotenv.get("DOTENV_PASSWORD"));
    }
}
