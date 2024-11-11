package com.workspacepi.apiquoteflow.application.cotacoes.produtos;

import com.workspacepi.apiquoteflow.domain.cotacoes.CotacoesRepository;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutosCotacaoService {

    private final ProdutosCotacaoRepository produtosCotacaoRepository;
    private final CotacoesRepository cotacaoRepository;

    public ProdutosCotacaoService(ProdutosCotacaoRepository produtosCotacaoRepository, CotacoesRepository cotacaoRepository) {
        this.produtosCotacaoRepository = produtosCotacaoRepository;
        this.cotacaoRepository = cotacaoRepository;
    }

    public List<ProdutosCotacao> findAllProdutosByCotacao(UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        return produtosCotacaoRepository.findAllProdutosByCotacao(id_cotacao);
    }

    public ProdutosCotacao findProdutosCotacaoById(UUID id_cotacao, UUID id_produto) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        return produtosCotacaoRepository.findProdutoByCotacaoAndId(id_cotacao, id_produto);
    }

    public ProdutosCotacao inserirProdutosCotacao(ProdutosCotacaoCreateCommand produtos, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        ProdutosCotacao produtosCotacaoDomain = produtos.toProdutoCotacao();
        produtosCotacaoRepository.inserirProdutosCotacao(produtosCotacaoDomain, id_cotacao);
        return findProdutosCotacaoById(id_cotacao, produtosCotacaoDomain.getId());
    }

    public ProdutosCotacao modificarProdutosCotacao(ProdutosCotacaoUpdateCommand produtos, UUID id_produto, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        ProdutosCotacao produtosCotacaoDomain = produtos.toProdutoCotacao(id_produto);
        produtosCotacaoRepository.modificarProdutosCotacao(produtosCotacaoDomain, id_produto, id_cotacao);
        return findProdutosCotacaoById(id_cotacao, produtosCotacaoDomain.getId());
    }

    public void deleteProdutosCotacaoById(UUID id_cotacao, UUID id_produto) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        if(cotacaoRepository.findById(id_produto) == null)
            throw new RuntimeException("Produto não encontrado");

        produtosCotacaoRepository.deleteProdutosByCotacaoAndId(id_cotacao, id_produto);
    }

}
