package com.workspacepi.apiquoteflow.application.enderecos.exceptions;

import java.util.UUID;

// Classe base para exceções de cotação
public abstract class EnderecosExceptions extends Exception {
    private final UUID id_endereco;

    public EnderecosExceptions(String mensagem, UUID id_endereco) {
        super(mensagem);
        this.id_endereco = id_endereco;
    }

    public UUID getId_endereco() {
        return id_endereco;
    }
}
