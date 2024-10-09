package com.workspacepi.apiquoteflow.application.enderecos.exceptions;

import lombok.Getter;

import java.util.UUID;

// Classe base para exceções de cotação
@Getter
public abstract class EnderecosExceptions extends Exception {
    private final UUID id_endereco;

    public EnderecosExceptions(String mensagem, UUID id_endereco) {
        super(mensagem);
        this.id_endereco = id_endereco;
    }

}
