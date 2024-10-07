package com.workspacepi.apiquoteflow.application.produtos.exceptions;

import java.util.UUID;

// Exceção específica para quando uma cotação não for encontrada
public class ProdutoNaoEncontradoException extends ProdutosExceptions {

    public ProdutoNaoEncontradoException(UUID id_produto) {
        super("Produto " + id_produto + " não encontrado", id_produto);
    }
}
