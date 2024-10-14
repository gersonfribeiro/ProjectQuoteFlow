package com.workspacepi.apiquoteflow.application.cotacoes.exceptions;

import lombok.Getter;

import java.util.UUID;

// Classe base para exceções de cotação
@Getter
public abstract class CotacoesExceptions extends Exception {
    private final UUID id_cotacao;

    public CotacoesExceptions(String mensagem, UUID id_cotacao) {
        super(mensagem);
        this.id_cotacao = id_cotacao;
    }

}
