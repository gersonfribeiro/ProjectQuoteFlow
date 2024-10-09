package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import lombok.Getter;

import java.util.UUID;

// Classe base para exceções de empresa
@Getter
public abstract class EmpresasExceptions extends Exception {
    private final UUID id_empresa;

    public EmpresasExceptions(String mensagem, UUID id_empresa) {
        super(mensagem);
        this.id_empresa = id_empresa;
    }

}
