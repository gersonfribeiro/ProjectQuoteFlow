package com.workspacepi.apiquoteflow.application.produtos.exceptions;

import lombok.Getter;

import java.util.UUID;

// Classe base para exceções de cotação
@Getter
public abstract class ProdutosExceptions extends Exception {
    private final UUID id_produto;

    public ProdutosExceptions(String mensagem, UUID id_produto) {
        super(mensagem);
        this.id_produto = id_produto;
    }

}
