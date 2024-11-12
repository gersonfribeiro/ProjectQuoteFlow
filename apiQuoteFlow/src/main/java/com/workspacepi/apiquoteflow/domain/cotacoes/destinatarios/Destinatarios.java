package com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Destinatarios {
    private UUID id;
    private Timestamp data_envio;
    private UUID id_cotacao;
    private UUID id_destinatario;

    public Destinatarios(UUID id_cotacao, UUID id_destinatario) {
        this.id = UUID.randomUUID();
        this.data_envio = Timestamp.from(Instant.now());
        this.id_cotacao = id_cotacao;
        this.id_destinatario = id_destinatario;
    }

    public Destinatarios(UUID id, UUID id_cotacao, UUID id_destinatario) {
        this.id = id;
        this.data_envio = Timestamp.from(Instant.now());
        this.id_cotacao = id_cotacao;
        this.id_destinatario = id_destinatario;
    }
}
