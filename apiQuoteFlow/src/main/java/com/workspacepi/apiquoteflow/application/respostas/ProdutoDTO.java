package com.workspacepi.apiquoteflow.application.respostas;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProdutoDTO {
    private UUID idProduto;
    private String categoria;
    private String descricao;
    private String observacao;
    private String variacao;
    private int quantidade;

}
