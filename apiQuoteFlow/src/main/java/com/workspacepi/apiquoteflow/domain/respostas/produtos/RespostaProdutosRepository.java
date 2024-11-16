package com.workspacepi.apiquoteflow.domain.respostas.produtos;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RespostaProdutosRepository {

    List<RespostaProdutos> respostaProdutos(UUID id_resposta);

    RespostaProdutos respostaProduto(UUID id_resposta, UUID id_produto);

    Boolean registrarRespostaProduto(UUID id_resposta, UUID id_produto);

    Boolean modificarRespostaProduto(UUID id_resposta, UUID id_produto);

    Boolean deletarRespostaProduto(UUID id_resposta, UUID id_produto);
}
