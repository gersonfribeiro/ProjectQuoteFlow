package com.workspacepi.apiquoteflow.application.respostas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import lombok.Getter;
import java.util.UUID;

@Getter
public class RespostaCotacaoCreateCommand {

    @JsonProperty("id_empresa_autora")
    private UUID id_empresa_autora;

    @JsonProperty("id_cotacao")
    private UUID id_cotacao;

    public RespostaCotacao toRespostaCotaca() {
        return new RespostaCotacao(id_empresa_autora, id_cotacao);
    }
}
