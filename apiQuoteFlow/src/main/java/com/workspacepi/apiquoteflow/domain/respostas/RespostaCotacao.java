package com.workspacepi.apiquoteflow.domain.respostas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RespostaCotacao {

    private UUID id_resposta;
    private Timestamp data_resposta;
    private UUID id_empresa_autora;
    private UUID id_cotacao;

    public RespostaCotacao(UUID id_empresa_autora, UUID id_cotacao) {
        this.id_resposta = UUID.randomUUID();
        this.data_resposta = Timestamp.from(Instant.now());
        this.id_empresa_autora = id_empresa_autora;
        this.id_cotacao = id_cotacao;
    }
}
