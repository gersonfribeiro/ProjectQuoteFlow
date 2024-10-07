package com.workspacepi.apiquoteflow.adapters.http.produtos;


import com.workspacepi.apiquoteflow.application.produtos.ProdutosCreateCommand;
import com.workspacepi.apiquoteflow.application.produtos.ProdutosService;
import com.workspacepi.apiquoteflow.application.produtos.ProdutosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

// Nosso Handler é um componente controlado pelo Spring para termos a injeção de dependência,
// sua função é chamar os Services

@Component
public class ProdutosHandler {

//    Definção do atributo dos serviços de cotação e seu construtor

    private final ProdutosService produtosService;
    public ProdutosHandler(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

//    Método findAll definidos nos Serviços de cotação, o retorno é uma lista de cotações

    public ResponseEntity<List<Produtos>> findAll() {
        List<Produtos> produtos = produtosService.findAll();
        return ResponseEntity.ok(produtos);
    }

//    Método findById definido nos Serviços de cotação, o retorno é uma cotação ou uma Exception

    public ResponseEntity<Produtos> findById(String id_produto) throws Exception{
        Produtos produto = produtosService.findById(UUID.fromString(id_produto));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<Produtos> cadastrarProduto(ProdutosCreateCommand produtosCreateCommand) throws Exception{
        Produtos produto = produtosService.cadastrarProduto(produtosCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    public ResponseEntity<Produtos> modificarProduto(ProdutosUpdateCommand produtosUpdateCommand, String id_produto) throws Exception{
        Produtos produto = produtosService.modificarProduto(produtosUpdateCommand, UUID.fromString(id_produto));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<String> deleteProdutoById(String id_produto) throws Exception {
        produtosService.deleteProdutoById(UUID.fromString(id_produto));
        return ResponseEntity.noContent().build();
    }
}
