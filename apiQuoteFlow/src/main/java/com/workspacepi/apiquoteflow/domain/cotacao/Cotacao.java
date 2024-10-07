package com.workspacepi.apiquoteflow.domain.cotacao;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

//  A nossa classe cotação que será usada na manipulação e persistência dos dados
//  atravez dos métodos crud e consultas sql feitas no código da aplicação

public class Cotacao {
    private UUID id_cotacao;
    private Timestamp data_cotacao;
    private int numero_cotacao;
    private CotacaoStatus status_cotacao;
    private UUID id_empresa_cotacao;

    //  Os itens vão se transformar em produtos!
    private Set<ItensCotacao> itens;

//  Construtores

    // Construtor para uso do RowMapper

    public Cotacao(UUID id_cotacao, Timestamp data_cotacao, int numero_cotacao, CotacaoStatus status_cotacao, UUID id_empresa_cotacao, Set<ItensCotacao> itens) {
        this.id_cotacao = id_cotacao;
        this.data_cotacao = data_cotacao;
        this.numero_cotacao = numero_cotacao;
        this.status_cotacao = status_cotacao;
        this.id_empresa_cotacao = id_empresa_cotacao;
        this.itens = itens;
    }

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

    public UUID getId_cotacao() {
        return id_cotacao;
    }

    public CotacaoStatus getStatus_cotacao() {
        return status_cotacao;
    }

    public Timestamp getData_cotacao() {
        return data_cotacao;
    }

    public int getNumero_cotacao() {
        return numero_cotacao;
    }

    public UUID getId_empresa_cotacao() {
        return id_empresa_cotacao;
    }

    public Set<ItensCotacao> getItens() {
        return itens;
    }

    public void setId_cotacao(UUID id_cotacao) {
        this.id_cotacao = id_cotacao;
    }

    public void setStatus_cotacao(CotacaoStatus status_cotacao) {
        this.status_cotacao = status_cotacao;
    }

    public void setData_cotacao(Timestamp dataCotacao) {
        this.data_cotacao = dataCotacao;
    }

    public void setNumero_cotacao(int numero_cotacao) {
        this.numero_cotacao = numero_cotacao;
    }

    public void setId_empresa_cotacao(UUID id_empresa_cotacao) {
        this.id_empresa_cotacao = id_empresa_cotacao;
    }

    public void setItens(Set<ItensCotacao> itens) {
        this.itens = itens;
    }
}
