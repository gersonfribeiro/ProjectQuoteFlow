package com.workspacepi.apiquoteflow.application.produtos.exceptions;

import lombok.Getter;

@Getter
public class ProdutoSkuNaoEncontradoException extends RuntimeException {

    private final String sku;

    public ProdutoSkuNaoEncontradoException(String sku) {
        super("O produto não foi encontrado para o código sku: " + sku);
        this.sku = sku;
    }
}
