package com.workspacepi.apiquoteflow.domain.cotacoes.produtos;

import java.util.List;
import java.util.UUID;

public interface ProdutosCotacaoRepository {

//  Buscar todas as cotações
    List<ProdutosCotacao> findAllProdutosByCotacao(UUID id_cotacao);

//  Buscar por ID
    ProdutosCotacao findProdutoByCotacaoAndId(UUID id_cotacao, UUID id_produto);

//  Inserção
    Boolean inserirProdutosCotacao(ProdutosCotacao produtos);

//  Modificação
    Boolean modificarProdutosCotacao(ProdutosCotacao produtos);

//  Delete
    Boolean deleteProdutosByCotacaoAndId(UUID id_cotacao, UUID id_produto);

}
