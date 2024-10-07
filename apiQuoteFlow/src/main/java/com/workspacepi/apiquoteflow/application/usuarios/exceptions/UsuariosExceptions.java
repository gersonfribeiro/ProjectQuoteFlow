package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

import java.util.UUID;

// Classe base para exceções de usuario
public abstract class UsuariosExceptions extends Exception {
    private final UUID id_usuario;

    public UsuariosExceptions(String mensagem, UUID id_usuario) {
        super(mensagem);
        this.id_usuario = id_usuario;
    }

    public UUID getid_usuario() {
        return id_usuario;
    }
}
