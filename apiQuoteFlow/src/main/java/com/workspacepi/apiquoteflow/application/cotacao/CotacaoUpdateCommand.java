package com.workspacepi.apiquoteflow.application.cotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.cotacao.Categoria;
import com.workspacepi.apiquoteflow.domain.cotacao.Cotacao;
import com.workspacepi.apiquoteflow.domain.cotacao.CotacaoStatus;
import com.workspacepi.apiquoteflow.domain.cotacao.ItensCotacao;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

//  Usando Jackson para serialização
public class CotacaoUpdateCommand {

    @JsonProperty("data_cotacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp data_cotacao;

    @JsonProperty("numero_cotacao")
    private int numero_cotacao;

    @JsonProperty("status_cotacao")
    private CotacaoStatus status_cotacao;

    @JsonProperty("id_empresa_cotacao")
    private UUID id_empresa_cotacao;

    @JsonProperty("itens")
    private Set<ItensCotacao> itens;


//  Conversão para cotacao
    public Cotacao toCotacao(UUID id_cotacao) {
        return new Cotacao(id_cotacao, data_cotacao, numero_cotacao, status_cotacao, id_empresa_cotacao, itens);
    }

//  Getters e setters

    public Timestamp getData_cotacao() {
        return data_cotacao;
    }

    public void setData_cotacao(Timestamp data_cotacao) {
        this.data_cotacao = data_cotacao;
    }

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

    public Set<ItensCotacao> getItens() {
        return itens;
    }

    public void setItens(Set<ItensCotacao> itens) {
        this.itens = itens;
    }
}
