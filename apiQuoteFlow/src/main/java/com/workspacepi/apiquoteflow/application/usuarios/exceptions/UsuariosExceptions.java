package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

import lombok.Getter;

import java.util.UUID;

// Classe base para exceções de usuario
@Getter
public abstract class UsuariosExceptions extends Exception {
    private final UUID id_usuario;

    public UsuariosExceptions(String mensagem, UUID id_usuario) {
        super(mensagem);
        this.id_usuario = id_usuario;
    }

}
