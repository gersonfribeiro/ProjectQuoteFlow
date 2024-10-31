package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEmailCadastradoException extends RuntimeException {

    private String email;

    public UsuarioEmailCadastradoException(String email) {
        super("Email já cadastrado: " + email);
        this.email = email;
    }
}
