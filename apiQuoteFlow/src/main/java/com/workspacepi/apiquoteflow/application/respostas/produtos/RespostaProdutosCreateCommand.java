package com.workspacepi.apiquoteflow.application.respostas.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutos;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RespostaProdutosCreateCommand {

    @JsonProperty("id_resposta")
    private UUID id_resposta;

    @JsonProperty("id_produto")
    private UUID id_produto;

    @JsonProperty("preco")
    private float preco;

    @JsonProperty("observacao")
    private String observacao;

    public RespostaProdutos toRespostaProdutos() {
        return new RespostaProdutos(id_resposta, id_produto, preco, observacao);
    }
}
