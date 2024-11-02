package com.workspacepi.apiquoteflow.adapters.http.produtos;


import com.workspacepi.apiquoteflow.application.produtos.ProdutosCreateCommand;
import com.workspacepi.apiquoteflow.application.produtos.ProdutosService;
import com.workspacepi.apiquoteflow.application.produtos.ProdutosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

// Nosso Handler é um componente controlado pelo Spring para termos a injeção de dependência,
// sua função é chamar os Services

@Component
public class ProdutosHandler {

    private final ProdutosService produtosService;

    public ProdutosHandler(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    public ResponseEntity<List<Produtos>> findAllByEmpresa(String id_empresa) {
        List<Produtos> produtos = produtosService.findAllByEmpresa(UUID.fromString(id_empresa));
        return ResponseEntity.ok(produtos);
    }

    public ResponseEntity<Produtos> findByIdAndEmpresa(String id_produto, String id_empresa) throws Exception {
        Produtos produto = produtosService.findByIdAndEmpresa(UUID.fromString(id_produto), UUID.fromString(id_empresa));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<Produtos> findBySKUAndEmpresa(String sku, String id_empresa) throws Exception {
        Produtos produto = produtosService.findBySKUAndEmpresa(sku, UUID.fromString(id_empresa));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<Produtos> cadastrarProdutoInEmpresa(ProdutosCreateCommand produtosCreateCommand, String id_empresa) throws Exception {
        Produtos produto = produtosService.cadastrarProdutoInEmpresa(produtosCreateCommand, UUID.fromString(id_empresa));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<Produtos> modificarProdutoInEmpresa(ProdutosUpdateCommand produtosUpdateCommand, String id_produto, String id_empresa) throws Exception {
        Produtos produto = produtosService.modificarProdutoInEmpresa(produtosUpdateCommand, UUID.fromString(id_empresa), UUID.fromString(id_produto));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<Void> deleteProdutoByIdAndEmpresa(String id_empresa, String id_produto) throws Exception {
        produtosService.deleteProdutoByIdAndEmpresa(UUID.fromString(id_empresa), UUID.fromString(id_produto));
        return ResponseEntity.noContent().build();
    }
}
