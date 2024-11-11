package com.workspacepi.apiquoteflow.adapters.http.cotacoes.produtos;

import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacoesCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacoesUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id_cotacao}/produtos/cotados")
public class ProdutosCotacoesController {

    private final ProdutosCotacoesHandler produtosCotacoesHandler;

    public ProdutosCotacoesController(ProdutosCotacoesHandler produtosCotacoesHandler) {
        this.produtosCotacoesHandler = produtosCotacoesHandler;
    }

    @GetMapping
    public ResponseEntity<List<ProdutosCotacao>> findAllProdutosByCotacao(@PathVariable String id_cotacao) {
        return produtosCotacoesHandler.findAllProdutosByCotacao(id_cotacao);
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<ProdutosCotacao>  findProdutoByCotacaoAndId(@PathVariable String id_cotacao, @PathVariable String id_produto) {
        return produtosCotacoesHandler.findProdutoByCotacaoAndId(id_cotacao, id_produto);
    }

    @PostMapping
    public ResponseEntity<ProdutosCotacao> inserirProdutosCotacao(@RequestBody @Valid ProdutosCotacoesCreateCommand produtos, @PathVariable String id_cotacao) {
        return produtosCotacoesHandler.inserirProdutosCotacao(produtos, id_cotacao);
    }

    @PutMapping("/{id_produto}")
    public ResponseEntity<ProdutosCotacao> modificarProdutosCotacao(@RequestBody @Valid ProdutosCotacoesUpdateCommand produto, @PathVariable String id_produto, @PathVariable String id_cotacao) {
        return produtosCotacoesHandler.modificarProdutosCotacao(produto,id_produto, id_cotacao);
    }

    @DeleteMapping("/{id_produto}")
    public ResponseEntity<String> deleteProdutosByCotacaoAndId(@PathVariable String id_cotacao, @PathVariable String id_produto) {
        return produtosCotacoesHandler.deleteProdutosByCotacaoAndId(id_cotacao, id_produto);
    }
}
