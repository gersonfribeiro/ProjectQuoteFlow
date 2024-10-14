package com.workspacepi.apiquoteflow.application.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.produtos.CategoriaProduto;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProdutosUpdateCommand {

    @JsonProperty("id_produto")
    @NotNull(message = "a id do produto é obrigatória!")
    private UUID id_produto;

    @JsonProperty("categoria")
    private CategoriaProduto categoria;

    @JsonProperty("descricao")
    @NotNull(message = "A descrição é obrigatória!")
    private String descricao;

    @JsonProperty("observacao")
    private String observacao;

    @JsonProperty("sku")
    @NotNull(message = "O código SKU é obrigatório!")
    private String sku;

    @JsonProperty("variacao")
    private String variacao;

    public Produtos toProduto(UUID id_produto) { return new Produtos(id_produto, categoria, descricao, observacao, sku, variacao); }

}
