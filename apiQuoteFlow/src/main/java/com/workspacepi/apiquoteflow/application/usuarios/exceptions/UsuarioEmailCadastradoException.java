package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

public class UsuarioEmailCadastradoException extends UsuariosExceptions{

    public UsuarioEmailCadastradoException(String email) {
        super("O email " + email + " já está registrado! ", email);
    }
}
