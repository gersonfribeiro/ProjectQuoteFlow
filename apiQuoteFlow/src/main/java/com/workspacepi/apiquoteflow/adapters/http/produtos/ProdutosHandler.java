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
        try {
            List<Produtos> produtos = produtosService.findAllByEmpresa(UUID.fromString(id_empresa));
            return ResponseEntity.ok(produtos);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID da empresa inválido", e);
        }
    }

    public ResponseEntity<Produtos> findByIdAndEmpresa(String id_empresa, String id_produto) throws Exception {
        try {
            Produtos produto = produtosService.findByIdAndEmpresa(UUID.fromString(id_empresa), UUID.fromString(id_produto));
            return ResponseEntity.ok(produto);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID inválido", e);
        }
    }

    public ResponseEntity<Produtos> cadastrarProdutoInEmpresa(ProdutosCreateCommand produtosCreateCommand, String id_empresa) throws Exception {
        try {
            Produtos produto = produtosService.cadastrarProdutoInEmpresa(produtosCreateCommand, UUID.fromString(id_empresa));
            return ResponseEntity.status(HttpStatus.CREATED).body(produto);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID da empresa inválido", e);
        }
    }

    public ResponseEntity<Produtos> modificarProdutoInEmpresa(ProdutosUpdateCommand produtosUpdateCommand, String id_produto, String id_empresa) throws Exception {
        try {
            Produtos produto = produtosService.modificarProdutoInEmpresa(produtosUpdateCommand, UUID.fromString(id_empresa), UUID.fromString(id_produto));
            return ResponseEntity.ok(produto);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID inválido", e);
        }
    }

    public ResponseEntity<Void> deleteProdutoByIdAndEmpresa(String id_empresa, String id_produto) throws Exception {
        try {
            produtosService.deleteProdutoByIdAndEmpresa(UUID.fromString(id_empresa), UUID.fromString(id_produto));
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID inválido", e);
        }
    }
}
