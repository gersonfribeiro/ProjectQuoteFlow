package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// Exceção específica para quando um usuario não for encontrado
@Getter
@Setter
public class UsuarioNaoEncontradoException extends RuntimeException {

    private final UUID id_usuario;

    public UsuarioNaoEncontradoException(UUID id_usuario) {
        super("Usuário não encontrado para o id: " + id_usuario);
        this.id_usuario = id_usuario;
    }
}
