package com.workspacepi.apiquoteflow.application.produtos.exceptions;

import java.util.UUID;

// Classe base para exceções de cotação
public abstract class ProdutosExceptions extends Exception {
    private final UUID id_produto;

    public ProdutosExceptions(String mensagem, UUID id_produto) {
        super(mensagem);
        this.id_produto = id_produto;
    }

    public UUID getId_produto() {
        return id_produto;
    }
}
