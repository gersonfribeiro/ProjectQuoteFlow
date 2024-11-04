package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import lombok.Getter;

@Getter
public class EmpresaCNPJCadastradoException extends RuntimeException {
    private final String cnpj;

    public EmpresaCNPJCadastradoException(String cnpj) {
        super("Empresa já registrada com o CNPJ: " + cnpj);
        this.cnpj = cnpj;
    }
}
