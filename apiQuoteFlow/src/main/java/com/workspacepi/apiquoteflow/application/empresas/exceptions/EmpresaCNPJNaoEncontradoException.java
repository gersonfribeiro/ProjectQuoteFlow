package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import lombok.Getter;

// Exceção específica para quando uma empresa não for encontrada
@Getter
public class EmpresaCNPJNaoEncontradoException extends RuntimeException {

    private final String cnpj;

    public EmpresaCNPJNaoEncontradoException(String cnpj) {
        super("Empresa não registrada para o CNPJ: " + cnpj);
        this.cnpj = cnpj;
    }
}
