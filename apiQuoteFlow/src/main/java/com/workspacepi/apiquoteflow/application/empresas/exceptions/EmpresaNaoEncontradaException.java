package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import lombok.Getter;

import java.util.UUID;

// Exceção específica para quando uma empresa não for encontrada
@Getter
public class EmpresaNaoEncontradaException extends RuntimeException {

    private final UUID id_empresa;

    public EmpresaNaoEncontradaException(UUID id_empresa) {
        super("Empresa não registrada para: " + id_empresa);
        this.id_empresa = id_empresa;
    }
}
