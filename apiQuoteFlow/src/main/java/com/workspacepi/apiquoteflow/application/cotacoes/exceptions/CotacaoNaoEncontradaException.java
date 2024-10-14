package com.workspacepi.apiquoteflow.application.cotacoes.exceptions;

import java.util.UUID;

// Exceção específica para quando uma cotação não for encontrada
public class CotacaoNaoEncontradaException extends CotacoesExceptions {

    public CotacaoNaoEncontradaException(UUID id_cotacao) {
        super("Cotacao " + id_cotacao + " não encontrada", id_cotacao);
    }
}
