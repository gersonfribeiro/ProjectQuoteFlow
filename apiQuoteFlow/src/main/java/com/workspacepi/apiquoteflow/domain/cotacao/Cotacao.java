package com.workspacepi.apiquoteflow.domain.cotacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

//  A nossa classe cotação que será usada na manipulação e persistência dos dados
//  atravez dos métodos crud e consultas sql feitas no código da aplicação

@Getter
@Setter
@AllArgsConstructor
public class Cotacao {
    private UUID id_cotacao;
    private Timestamp data_cotacao;
    private int numero_cotacao;
    private CotacaoStatus status_cotacao;
    private UUID id_empresa_cotacao;

    //  Os itens vão se transformar em produtos!
    private Set<ItensCotacao> itens;

//  Construtores

    // Construtor para uso da inserção no banco de dados

    public Cotacao(int numero_cotacao, CotacaoStatus status_cotacao, UUID id_empresa_cotacao, Set<ItensCotacao> itens) {
        this.id_cotacao = UUID.randomUUID();
        this.data_cotacao = Timestamp.from(Instant.now());
        this.numero_cotacao = numero_cotacao;
        this.status_cotacao = status_cotacao;
        this.id_empresa_cotacao = id_empresa_cotacao;
        this.itens = itens;
    }

    // Construtor para uso da modificação no banco de dados

    public Cotacao(UUID id_cotacao, CotacaoStatus status_cotacao, UUID id_empresa_cotacao, Set<ItensCotacao> itens) {
        this.id_cotacao = id_cotacao;
        this.data_cotacao = Timestamp.from(Instant.now());
        this.status_cotacao = status_cotacao;
        this.id_empresa_cotacao = id_empresa_cotacao;
        this.itens = itens;
    }

}
