package com.workspacepi.apiquoteflow.application.respostas;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProdutoDTO {
    private UUID idProduto;
    private String descricao;
    private int quantidade;

}
