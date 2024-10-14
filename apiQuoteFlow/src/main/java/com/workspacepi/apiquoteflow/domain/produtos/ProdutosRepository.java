package com.workspacepi.apiquoteflow.domain.produtos;

import java.util.List;
import java.util.UUID;

// Nossa interface produtosRepository que define a abstração dos métodos de consulta
public interface ProdutosRepository {

//    Implementações individuais:
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


//    Novas implementações (definitivas):

//    Buscar todos os produtos de uma empresa, agora teremos um findAll porém com restrição de empresas.
//    Seu retorno é uma lista de produtos e o parâmetro e o id da empresa:

    List<Produtos> findAllByEmpresa(UUID id_empresa);

//    Agora basta seguir a lógica de implementar a restrição para todos os métodos:

    Produtos findByIdAndEmpresa(UUID id_produto, UUID id_empresa);

    Boolean cadastrarProdutoInEmpresa(Produtos produto, UUID id_empresa);

    Boolean modificarProdutoInEmpresa(Produtos produto, UUID id_empresa);

    Boolean deleteProdutoByIdAndEmpresa(UUID id_produto, UUID id_empresa);

}
