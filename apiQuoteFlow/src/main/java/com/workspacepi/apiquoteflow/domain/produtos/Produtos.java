package com.workspacepi.apiquoteflow.domain.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Produtos {
    private String categoria_produto;
    private String descricao_produto;
    private String observacao_produto;
    private String sku_produto;
    private UUID id_empresa_produto;
    private UUID id_produto;

    // Construtor de modificação
    public Produtos(String categoria_produto, String descricao_produto, String observacao_produto,
                   String sku_produto, UUID id_empresa_produto) {
        this.categoria_produto = categoria_produto;
        this.descricao_produto = descricao_produto;
        this.observacao_produto = observacao_produto;
        this.sku_produto = sku_produto;
        this.id_empresa_produto = id_empresa_produto;
        this.id_produto = UUID.randomUUID();
    }

}
