package com.workspacepi.apiquoteflow.application.produtos.exceptions;

import lombok.Getter;

@Getter
public class ProdutoSkuCadastradoException extends RuntimeException {

    private final String sku;

    public ProdutoSkuCadastradoException(String sku) {
        super("Código SKU já cadastrado: " + sku);
        this.sku = sku;
    }
}
