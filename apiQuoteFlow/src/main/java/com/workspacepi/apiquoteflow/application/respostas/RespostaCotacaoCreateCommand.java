package com.workspacepi.apiquoteflow.application.respostas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import lombok.Getter;
import java.util.UUID;

@Getter
public class RespostaCotacaoCreateCommand {

    @JsonProperty("id_empresa_resposta")
    private UUID id_empresa_resposta;

    @JsonProperty("id_cotacao")
    private UUID id_cotacao;

    public RespostaCotacao toRespostaCotacao() {
        return new RespostaCotacao(id_empresa_resposta, id_cotacao);
    }
}
