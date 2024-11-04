package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

public class UsuarioNaoAutenticadoException extends RuntimeException {

    public UsuarioNaoAutenticadoException() {
        super("Usuário não autenticado. Faça login para continuar.");
    }
}
