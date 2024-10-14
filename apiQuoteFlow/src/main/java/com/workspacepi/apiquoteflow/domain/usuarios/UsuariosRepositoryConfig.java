package com.workspacepi.apiquoteflow.domain.usuarios;

import com.workspacepi.apiquoteflow.domain.usuarios.impl.UsuariosRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuariosRepositoryConfig {

    @Bean
    public UsuariosRepository usuariosRepository() {
        return new UsuariosRepositoryImpl(); // Retorna a implementação do repositório
    }
}