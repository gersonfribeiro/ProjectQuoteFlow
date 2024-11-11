package com.workspacepi.apiquoteflow.domain.cotacoes;

import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

//  A nossa classe cotação que será usada na manipulação e persistência dos dados
//  atravez dos métodos crud e consultas sql feitas no código da aplicação

@Getter
@Setter
@AllArgsConstructor
public class Cotacoes {
    private UUID id_cotacao;
    private Timestamp data;
    private int numero;
    private CotacaoStatus status;
    private UUID id_empresa;
    private List<ProdutosCotacao> produtos;
    private List<Destinatarios> destinatarios;

//  Construtores

    // Construtor para uso da inserção no banco de dados

    public Cotacoes(int numero, CotacaoStatus status, UUID id_empresa, List<ProdutosCotacao> produtos, List<Destinatarios> destinatarios) {
        this.id_cotacao = UUID.randomUUID();
        this.data = Timestamp.from(Instant.now());
        this.numero = numero;
        this.status = status;
        this.id_empresa = id_empresa;
        this.produtos = produtos;
        this.destinatarios = destinatarios;
    }

    // Construtor para uso da modificação no banco de dados

    public Cotacoes(UUID id_cotacao, int numero, CotacaoStatus status, UUID id_empresa, List<ProdutosCotacao> produtos, List<Destinatarios> destinatarios) {
        this.id_cotacao = id_cotacao;
        this.data = Timestamp.from(Instant.now());
        this.numero = numero;
        this.status = status;
        this.id_empresa = id_empresa;
        this.produtos = produtos;
        this.destinatarios = destinatarios;
    }

}
