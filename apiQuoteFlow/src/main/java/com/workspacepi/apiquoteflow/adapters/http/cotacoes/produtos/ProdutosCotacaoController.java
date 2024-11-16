package com.workspacepi.apiquoteflow.adapters.http.cotacoes.produtos;

import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacaoCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.produtos.ProdutosCotacaoUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id_cotacao}/produtos/cotados")
public class ProdutosCotacaoController {

    private final ProdutosCotacaoHandler produtosCotacaoHandler;

    public ProdutosCotacaoController(ProdutosCotacaoHandler produtosCotacaoHandler) {
        this.produtosCotacaoHandler = produtosCotacaoHandler;
    }

    @GetMapping
    public ResponseEntity<List<ProdutosCotacao>> findAllProdutosByCotacao(@PathVariable String id_cotacao) {
        return produtosCotacaoHandler.findAllProdutosByCotacao(id_cotacao);
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<ProdutosCotacao> findProdutoByCotacaoAndId(@PathVariable String id_cotacao, @PathVariable String id_produto) {
        return produtosCotacaoHandler.findProdutoByCotacaoAndId(id_cotacao, id_produto);
    }

    @PostMapping
    public ResponseEntity<ProdutosCotacao> inserirProdutosCotacao(@RequestBody ProdutosCotacaoCreateCommand produtos, @PathVariable String id_cotacao) {
        return produtosCotacaoHandler.inserirProdutosCotacao(produtos, id_cotacao);
    }

    @PutMapping("/{id_produto}")
    public ResponseEntity<ProdutosCotacao> modificarProdutosCotacao(@RequestBody ProdutosCotacaoUpdateCommand produto,
                                                                    @PathVariable String id_cotacao,
                                                                    @PathVariable String id_produto) {
        return produtosCotacaoHandler.modificarProdutosCotacao(produto, id_cotacao, id_produto);
    }

    @DeleteMapping("/{id_produto}")
    public ResponseEntity<String> deleteProdutosByCotacaoAndId(@PathVariable String id_cotacao, @PathVariable String id_produto) {
        return produtosCotacaoHandler.deleteProdutosByCotacaoAndId(id_cotacao, id_produto);
    }
}
