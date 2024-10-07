package com.workspacepi.apiquoteflow.domain.produtos;

import java.util.List;
import java.util.UUID;

// Nossa interface produtosRepository que define a abstração dos métodos de consulta
public interface ProdutosRepository {

//  Buscar todas as produtos
    List<Produtos> findAll();

//  Buscar por ID
    Produtos findById(UUID id_produto);

//  Inserção
    Boolean cadastrarProduto(Produtos produto);

//  Modificação
    Boolean modificarProduto(Produtos produto);

//  Delete
    Boolean deleteProdutoById(UUID id_produto);
}
