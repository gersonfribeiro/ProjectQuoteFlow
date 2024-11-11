package com.workspacepi.apiquoteflow.application.cotacoes.destinatarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DestinatariosUpdateCommand {

    @JsonProperty("id_cotacao")
    private UUID id_cotacao;

    @JsonProperty("id_destinatario")
    private UUID id_destinatario;

    public Destinatarios toDestinatario(UUID id) {
        return new Destinatarios(id, id_cotacao, id_destinatario);
    }
}
