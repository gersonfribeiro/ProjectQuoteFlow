package com.workspacepi.apiquoteflow.application.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.produtos.CategoriaProduto;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProdutosCreateCommand {


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


    public Produtos toProduto() { return new Produtos(categoria, descricao, observacao, sku, variacao); }

}
