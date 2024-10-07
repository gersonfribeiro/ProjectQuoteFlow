package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

import java.util.UUID;

// Exceção específica para quando um usuario não for encontrado
public class UsuarioNaoEncontradoException extends UsuariosExceptions {

    public UsuarioNaoEncontradoException(UUID id_usuario) {
        super("Usuário " + id_usuario + " não encontrado", id_usuario);
    }
}
