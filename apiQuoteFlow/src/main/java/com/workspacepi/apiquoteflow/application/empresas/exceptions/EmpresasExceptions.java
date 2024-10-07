package com.workspacepi.apiquoteflow.application.empresas.exceptions;

import java.util.UUID;

// Classe base para exceções de empresa
public abstract class EmpresasExceptions extends Exception {
    private final UUID id_empresa;

    public EmpresasExceptions(String mensagem, UUID id_empresa) {
        super(mensagem);
        this.id_empresa = id_empresa;
    }

    public UUID getId_empresa() {
        return id_empresa;
    }
}
