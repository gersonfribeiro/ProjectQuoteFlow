package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import java.util.UUID;

// Exceção específica para quando uma empresa não for encontrada
public class EmpresaNaoEncontradaException extends EmpresasExceptions {

    public EmpresaNaoEncontradaException(UUID id_empresa) {
        super("Empresa " + id_empresa + " não encontrada", id_empresa);
    }
}
