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
    private UUID id_resposta;
    private UUID id_produto;
    private float preco;
    private String observacao;

    public RespostaProdutos(UUID id_resposta, UUID id_produto, float preco, String observacao) {
        this.id = UUID.randomUUID();
        this.id_resposta = id_resposta;
        this.id_produto = id_produto;
        this.preco = preco;
        this.observacao = observacao;
    }
}
