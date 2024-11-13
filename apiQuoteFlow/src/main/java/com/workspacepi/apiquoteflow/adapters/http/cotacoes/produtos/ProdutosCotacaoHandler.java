package com.workspacepi.apiquoteflow.adapters.http.cotacoes.produtos;

import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacaoCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacaoService;
import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacaoUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProdutosCotacaoHandler {

    private final ProdutosCotacaoService produtosCotacaoService;

    public ProdutosCotacaoHandler(ProdutosCotacaoService produtosCotacaoService) {
        this.produtosCotacaoService = produtosCotacaoService;
    }

    public ResponseEntity<List<ProdutosCotacao>> findAllProdutosByCotacao(String id_cotacao) {
        List<ProdutosCotacao> produtos = produtosCotacaoService.findAllProdutosByCotacao(UUID.fromString(id_cotacao));
        return ResponseEntity.ok(produtos);
    }

    public ResponseEntity<ProdutosCotacao> findProdutoByCotacaoAndId(String id_cotacao, String id_produto) {
        ProdutosCotacao produto = produtosCotacaoService.findProdutosByCotacaoAndId(UUID.fromString(id_cotacao), UUID.fromString(id_produto));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<ProdutosCotacao> inserirProdutosCotacao(ProdutosCotacaoCreateCommand produtos, String id_cotacao) {
        ProdutosCotacao produtosDomain = produtosCotacaoService.inserirProdutosCotacao(produtos, UUID.fromString(id_cotacao));
        return ResponseEntity.ok(produtosDomain);
    }

    public ResponseEntity<ProdutosCotacao> modificarProdutosCotacao(ProdutosCotacaoUpdateCommand produtos, String id, String id_cotacao) {
        ProdutosCotacao produtosDomain = produtosCotacaoService.modificarProdutosCotacao(produtos, UUID.fromString(id), UUID.fromString(id_cotacao));
        return ResponseEntity.ok(produtosDomain);
    }

    public ResponseEntity<String> deleteProdutosByCotacaoAndId(String id_cotacao, String id_produto) {
        produtosCotacaoService.deleteProdutosCotacaoById(UUID.fromString(id_cotacao), UUID.fromString(id_produto));
        return ResponseEntity.noContent().build();
    }

}
