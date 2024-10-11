package com.workspacepi.apiquoteflow.application.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.produtos.Categoria;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProdutosCreateCommand {

    @JsonProperty("categoria")
    private Categoria categoria_produto;

    @JsonProperty("descricao")
    private String descricao_produto;

    @JsonProperty("observacao")
    private String observacao_produto;

    @JsonProperty("sku")
    private String sku_produto;

    @JsonProperty("id_empresa")
    private UUID id_empresa_produto;

    @JsonProperty("id_produto")
    private UUID id_produto;

    public Produtos toProduto() { return new Produtos(categoria_produto, descricao_produto,
            observacao_produto, sku_produto, id_empresa_produto); }

}
