package com.workspacepi.apiquoteflow.application.cotacao.exceptions;

import java.util.UUID;

// Classe base para exceções de cotação
public abstract class CotacaoExceptions extends Exception {
    private final UUID id_cotacao;

    public CotacaoExceptions(String mensagem, UUID id_cotacao) {
        super(mensagem);
        this.id_cotacao = id_cotacao;
    }

    public UUID getid_cotacao() {
        return id_cotacao;
    }
}

