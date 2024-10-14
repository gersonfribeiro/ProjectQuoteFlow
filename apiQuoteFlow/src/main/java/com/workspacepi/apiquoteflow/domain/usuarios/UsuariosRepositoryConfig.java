package com.workspacepi.apiquoteflow.domain.usuarios;

import com.workspacepi.apiquoteflow.domain.usuarios.UsuariosRepository;
import com.workspacepi.apiquoteflow.domain.usuarios.impl.UsuariosRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UsuariosRepositoryConfig {

    @Bean
    public UsuariosRepository usuariosRepository(JdbcTemplate jdbcTemplate) {
        // Passa o JdbcTemplate para o construtor do UsuariosRepositoryImpl
        return new UsuariosRepositoryImpl(jdbcTemplate);
    }
}
