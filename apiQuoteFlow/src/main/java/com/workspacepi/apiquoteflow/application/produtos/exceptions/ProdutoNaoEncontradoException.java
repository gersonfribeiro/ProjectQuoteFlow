package com.workspacepi.apiquoteflow.application.produtos.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// Exceção específica para quando uma cotação não for encontrada
@Getter
@Setter
public class ProdutoNaoEncontradoException extends RuntimeException {

    private final UUID id_produto;

    public ProdutoNaoEncontradoException(UUID id_produto) {
        super("Produto " + id_produto + " não encontrado");
        this.id_produto = id_produto;
    }
}
