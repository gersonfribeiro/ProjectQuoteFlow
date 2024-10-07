package com.workspacepi.apiquoteflow.application.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;

import java.util.UUID;

public class ProdutosUpdateCommand {


    @JsonProperty("categoria_produto")
    private String categoria_produto;

    @JsonProperty("descricao_produto")
    private String descricao_produto;

    @JsonProperty("observacao_produto")
    private String observacao_produto;

    @JsonProperty("sku_produto")
    private String sku_produto;

    @JsonProperty("id_empresa_produto")
    private UUID id_empresa_produto;

    @JsonProperty("id_produto")
    private UUID id_produto;

    public Produtos toProduto(UUID id_produto) { return new Produtos(categoria_produto, descricao_produto, observacao_produto, sku_produto, id_empresa_produto, id_produto); }

    public String getCategoria_produto() {
        return categoria_produto;
    }

    public void setCategoria_produto(String categoria_produto) {
        this.categoria_produto = categoria_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public String getObservacao_produto() {
        return observacao_produto;
    }

    public void setObservacao_produto(String observacao_produto) {
        this.observacao_produto = observacao_produto;
    }

    public String getSku_produto() {
        return sku_produto;
    }

    public void setSku_produto(String sku_produto) {
        this.sku_produto = sku_produto;
    }

    public UUID getId_empresa_produto() {
        return id_empresa_produto;
    }

    public void setId_empresa_produto(UUID id_empresa_produto) {
        this.id_empresa_produto = id_empresa_produto;
    }

    public UUID getId_produto() {
        return id_produto;
    }

    public void setId_produto(UUID id_produto) {
        this.id_produto = id_produto;
    }
}
