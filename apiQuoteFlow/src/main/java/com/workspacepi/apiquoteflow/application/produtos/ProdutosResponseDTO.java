package com.workspacepi.apiquoteflow.application.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProdutosResponseDTO {

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("variacao")
    private String variacao;

    @JsonProperty("observacao")
    private String observacao;

    public ProdutosResponseDTO(String sku, String descricao, String variacao, String observacao) {
        this.sku = sku;
        this.descricao = descricao;
        this.variacao = variacao;
        this.observacao = observacao;
    }
}
