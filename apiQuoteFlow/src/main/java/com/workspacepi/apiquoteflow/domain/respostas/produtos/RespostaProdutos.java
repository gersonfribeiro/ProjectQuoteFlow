package com.workspacepi.apiquoteflow.domain.respostas.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RespostaProdutos {

    private UUID id;
    private String observacao;
    private float preco;
    private UUID id_produto;
    private UUID id_resposta;

    public RespostaProdutos(String observacao, float preco, UUID id_produto, UUID id_resposta) {
        this.id = UUID.randomUUID();
        this.observacao = observacao;
        this.preco = preco;
        this.id_produto = id_produto;
        this.id_resposta = id_resposta;
    }
}
