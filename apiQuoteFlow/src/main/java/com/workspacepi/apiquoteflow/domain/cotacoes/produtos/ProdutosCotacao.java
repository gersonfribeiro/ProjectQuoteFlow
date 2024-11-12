package com.workspacepi.apiquoteflow.domain.cotacoes.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// Uma segunda tabela criada para definir um relacionamento entre as cotações e seus itens

@Getter
@Setter
@AllArgsConstructor
public class ProdutosCotacao {
    private UUID id;
    private UUID id_cotacao;
    private UUID id_produto;
    private int quantidade;

    public ProdutosCotacao(UUID id_cotacao, UUID id_produto, int quantidade) {
        this.id = UUID.randomUUID();
        this.id_cotacao = id_cotacao;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }
}
