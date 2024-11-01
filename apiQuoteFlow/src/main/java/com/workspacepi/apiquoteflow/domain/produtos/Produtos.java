package com.workspacepi.apiquoteflow.domain.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Produtos {
    private UUID id_produto;
    private CategoriaProduto categoria;
    private String descricao;
    private String observacao;
    private String sku;
    private String variacao;
    private UUID id_empresa;

    // Construtor de inserção
    public Produtos(CategoriaProduto categoria, String descricao, String observacao,
                    String sku, String variacao, UUID id_empresa) {
        this.id_produto = UUID.randomUUID();
        this.categoria = categoria;
        this.descricao = descricao;
        this.observacao = observacao;
        this.sku = sku;
        this.variacao = variacao;
        this.id_empresa = id_empresa;
    }

//    Construtor para o DTO de create
    public Produtos(CategoriaProduto categoria, String descricao, String observacao,
                    String sku, String variacao) {
        this.id_produto = UUID.randomUUID();
        this.categoria = categoria;
        this.descricao = descricao;
        this.observacao = observacao;
        this.sku = sku;
        this.variacao = variacao;
    }

//    Construtor para o DTO de update
    public Produtos(UUID id_produto, CategoriaProduto categoria, String descricao, String observacao,
                    String sku, String variacao) {
        this.id_produto = id_produto;
        this.categoria = categoria;
        this.descricao = descricao;
        this.observacao = observacao;
        this.sku = sku;
        this.variacao = variacao;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id_produto=" + id_produto +
                ", sku='" + sku + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", variacao='" + variacao + '\'' +
                ", observacao='" + observacao + '\'' +
                ", id_empresa=" + id_empresa +
                '}';
    }
}
