package com.workspacepi.apiquoteflow.application.cotacao;

import com.workspacepi.apiquoteflow.domain.cotacao.Cotacao;
import com.workspacepi.apiquoteflow.domain.cotacao.CotacaoStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//  Usando Jackson para serialização
@Setter
@Getter
public class CotacaoCreateCommand {

    @JsonProperty("numero_cotacao")
    private int numero_cotacao;

    @JsonProperty("status")
    private CotacaoStatus status_cotacao;

    @JsonProperty("id_empresa")
    private UUID id_empresa_cotacao;

//  Conversão para cotacao
    public Cotacao toCotacao() {
        return new Cotacao(numero_cotacao, status_cotacao, id_empresa_cotacao, null);
    }

}
