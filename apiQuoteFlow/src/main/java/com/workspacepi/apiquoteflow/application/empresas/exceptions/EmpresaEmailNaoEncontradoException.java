
package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import lombok.Getter;

@Getter
public class EmpresaEmailNaoEncontradoException extends RuntimeException {
    private final String email;

    public EmpresaEmailNaoEncontradoException(String email) {
        super("Empresa não encontrada com o email: " + email);
        this.email = email;
    }
}
