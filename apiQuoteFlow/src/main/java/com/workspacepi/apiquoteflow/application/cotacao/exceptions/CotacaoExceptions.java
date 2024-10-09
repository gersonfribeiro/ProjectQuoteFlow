package com.workspacepi.apiquoteflow.application.cotacao.exceptions;

import lombok.Getter;

import java.util.UUID;

// Classe base para exceções de cotação
@Getter
public abstract class CotacaoExceptions extends Exception {
    private final UUID id_cotacao;

    public CotacaoExceptions(String mensagem, UUID id_cotacao) {
        super(mensagem);
        this.id_cotacao = id_cotacao;
    }

}
