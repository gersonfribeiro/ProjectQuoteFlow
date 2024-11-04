package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import lombok.Getter;

@Getter
public class EmpresaEmailCadastradoException extends RuntimeException {
    private final String email;

    public EmpresaEmailCadastradoException(String email) {
        super("Empresa já registrada com o email: " + email);
        this.email = email;
    }
}
