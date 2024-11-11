package com.workspacepi.apiquoteflow.adapters.http.cotacoes.produtos;

import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacoesCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacoesService;
import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacoesUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProdutosCotacoesHandler {

    private final ProdutosCotacoesService produtosCotacoesService;

    public ProdutosCotacoesHandler(ProdutosCotacoesService produtosCotacoesService) {
        this.produtosCotacoesService = produtosCotacoesService;
    }

    public ResponseEntity<List<ProdutosCotacao>> findAllProdutosByCotacao(String id_cotacao) {
        List<ProdutosCotacao> produtos = produtosCotacoesService.findAllProdutosByCotacao(UUID.fromString(id_cotacao));
        return ResponseEntity.ok(produtos);
    }

    public ResponseEntity<ProdutosCotacao> findProdutoByCotacaoAndId(String id_cotacao, String id_produto) {
        ProdutosCotacao produto = produtosCotacoesService.findProdutosCotacaoById(UUID.fromString(id_cotacao), UUID.fromString(id_produto));
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<ProdutosCotacao> inserirProdutosCotacao(ProdutosCotacoesCreateCommand produtos, String id_cotacao) {
        ProdutosCotacao produtosDomain = produtosCotacoesService.inserirProdutosCotacao(produtos, UUID.fromString(id_cotacao));
        return ResponseEntity.ok(produtosDomain);
    }

    public ResponseEntity<ProdutosCotacao> modificarProdutosCotacao(ProdutosCotacoesUpdateCommand produtos, String id_produto, String id_cotacao) {
        ProdutosCotacao produtosDomain = produtosCotacoesService.modificarProdutosCotacao(produtos, UUID.fromString(id_produto), UUID.fromString(id_cotacao));
        return ResponseEntity.ok(produtosDomain);
    }

    public ResponseEntity<String> deleteProdutosByCotacaoAndId(String id_cotacao, String id_produto) {
        produtosCotacoesService.deleteProdutosCotacaoById(UUID.fromString(id_cotacao), UUID.fromString(id_produto));
        return ResponseEntity.noContent().build();
    }

}
