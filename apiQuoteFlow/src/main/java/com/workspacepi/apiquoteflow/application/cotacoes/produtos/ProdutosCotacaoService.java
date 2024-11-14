package com.workspacepi.apiquoteflow.application.cotacoes.produtos;

import com.workspacepi.apiquoteflow.domain.cotacoes.Cotacoes;
import com.workspacepi.apiquoteflow.domain.cotacoes.CotacoesRepository;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacaoRepository;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import com.workspacepi.apiquoteflow.domain.produtos.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutosCotacaoService {

    private final ProdutosCotacaoRepository produtosCotacaoRepository;
    private final CotacoesRepository cotacaoRepository;
    private final ProdutosRepository produtosRepository;

    public ProdutosCotacaoService(ProdutosCotacaoRepository produtosCotacaoRepository,
                                  CotacoesRepository cotacaoRepository,
                                  ProdutosRepository produtosRepository) {
        this.produtosCotacaoRepository = produtosCotacaoRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.produtosRepository = produtosRepository;
    }

    public List<ProdutosCotacao> findAllProdutosByCotacao(UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        return produtosCotacaoRepository.findAllProdutosByCotacao(id_cotacao);
    }

    public ProdutosCotacao findProdutosByCotacaoAndId(UUID id_cotacao, UUID id_produto) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        return produtosCotacaoRepository.findProdutoByCotacaoAndId(id_cotacao, id_produto);
    }

    public ProdutosCotacao inserirProdutosCotacao(ProdutosCotacaoCreateCommand produtos, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        if (produtosCotacaoRepository.findProdutoByCotacaoAndId(id_cotacao, produtos.getId_produto()) != null)
            throw new RuntimeException("Produto já registrado na cotação");


        ProdutosCotacao produtosCotacaoDomain = produtos.toProdutoCotacao();
        produtosCotacaoRepository.inserirProdutosCotacao(produtosCotacaoDomain, id_cotacao);
        return findProdutosByCotacaoAndId(id_cotacao, produtosCotacaoDomain.getId_produto());
    }

    public ProdutosCotacao modificarProdutosCotacao(ProdutosCotacaoUpdateCommand produtos, UUID id_produto, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        ProdutosCotacao produtosCotacaoDomain = produtos.toProdutoCotacao(id_produto);
        produtosCotacaoRepository.modificarProdutosCotacao(produtosCotacaoDomain, id_produto, id_cotacao);
        return findProdutosByCotacaoAndId(id_cotacao, produtosCotacaoDomain.getId());
    }

    public void deleteProdutosCotacaoById(UUID id_cotacao, UUID id_produto) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacão não encontrada");

        produtosCotacaoRepository.deleteProdutosByCotacaoAndId(id_cotacao, id_produto);
    }

}
