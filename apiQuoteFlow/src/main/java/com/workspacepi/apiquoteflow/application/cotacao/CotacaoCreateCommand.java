package com.workspacepi.apiquoteflow.application.cotacao;

import com.workspacepi.apiquoteflow.domain.cotacao.Cotacao;
import com.workspacepi.apiquoteflow.domain.cotacao.CotacaoStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

//  Usando Jackson para serialização
public class CotacaoCreateCommand {

    @JsonProperty("numero_cotacao")
    private int numero_cotacao;

    @JsonProperty("status_cotacao")
    private CotacaoStatus status_cotacao;

    @JsonProperty("id_empresa_cotacao")
    private UUID id_empresa_cotacao;

//  Conversão para cotacao
    public Cotacao toCotacao() {
        return new Cotacao(numero_cotacao, status_cotacao, id_empresa_cotacao, null);
    }

//  Getters e setters

    public int getNumero_cotacao() {
        return numero_cotacao;
    }

    public void setNumero_cotacao(int numero_cotacao) {
        this.numero_cotacao = numero_cotacao;
    }

    public CotacaoStatus getStatus_cotacao() {
        return status_cotacao;
    }

    public void setStatus_cotacao(CotacaoStatus status_cotacao) {
        this.status_cotacao = status_cotacao;
    }

    public UUID getId_empresa_cotacao() {
        return id_empresa_cotacao;
    }

    public void setId_empresa_cotacao(UUID id_empresa_cotacao) {
        this.id_empresa_cotacao = id_empresa_cotacao;
    }
}
