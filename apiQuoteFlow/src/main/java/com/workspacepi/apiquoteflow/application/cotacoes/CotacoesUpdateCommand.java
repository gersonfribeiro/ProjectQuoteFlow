package com.workspacepi.apiquoteflow.application.cotacoes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.cotacoes.Cotacoes;
import com.workspacepi.apiquoteflow.domain.cotacoes.CotacaoStatus;
import com.workspacepi.apiquoteflow.domain.cotacoes.ProdutosCotacao;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

//  Usando Jackson para serialização
@Setter
@Getter
public class CotacoesUpdateCommand {

    @JsonProperty("numero")
    private int numero;

    @JsonProperty("status")
    private CotacaoStatus status;

    @JsonProperty("id_empresa")
    private UUID id_empresa;

    @JsonProperty("produtos")
    private Set<ProdutosCotacao> produtos;

//  Conversão para cotacao
    public Cotacoes toCotacao(UUID id_cotacao) {
        return new Cotacoes(id_cotacao, numero, status, id_empresa, produtos);
    }

}
