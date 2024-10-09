package com.workspacepi.apiquoteflow.application.cotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.cotacao.Cotacao;
import com.workspacepi.apiquoteflow.domain.cotacao.CotacaoStatus;
import com.workspacepi.apiquoteflow.domain.cotacao.ItensCotacao;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

//  Usando Jackson para serialização
@Setter
@Getter
public class CotacaoUpdateCommand {

    @JsonProperty("data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp data_cotacao;

    @JsonProperty("numero_cotacao")
    private int numero_cotacao;

    @JsonProperty("status")
    private CotacaoStatus status_cotacao;

    @JsonProperty("id_empresa")
    private UUID id_empresa_cotacao;

    @JsonProperty("itens")
    private Set<ItensCotacao> itens;


//  Conversão para cotacao
    public Cotacao toCotacao(UUID id_cotacao) {
        return new Cotacao(id_cotacao, data_cotacao, numero_cotacao, status_cotacao, id_empresa_cotacao, itens);
    }

}
