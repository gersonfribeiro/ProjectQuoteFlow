package com.workspacepi.apiquoteflow.application.cotacoes.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import lombok.Getter;
import java.util.UUID;

@Getter
public class ProdutosCotacoesCreateCommand {

    @JsonProperty("id_cotacao")
    private UUID id_cotacao;

    @JsonProperty("id_produto")
    private UUID id_produto;

    @JsonProperty("quantidade")
    private Integer quantidade;

    public ProdutosCotacao toProdutoCotacao() {
        return new ProdutosCotacao(id_cotacao, id_produto, quantidade);
    }

}
