package com.workspacepi.apiquoteflow.application.respostas.produtos;

import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutos;
import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RespostaProdutosService {

    private final RespostaProdutosRepository respostaProdutos;

    public RespostaProdutosService(RespostaProdutosRepository respostaProdutosRepository) {
        this.respostaProdutos = respostaProdutosRepository;
    }

    public List<RespostaProdutos> respostaProdutos(UUID id_resposta) {
        return respostaProdutos.respostaProdutos(id_resposta);
    }

    public RespostaProdutos respostaProduto(UUID id_resposta, UUID id_produto) {
        return respostaProdutos.respostaProduto(id_resposta, id_produto);
    }

    public RespostaProdutos registrarRespostaProduto(RespostaProdutosCreateCommand resposta, UUID id_produto) {

        RespostaProdutos respostaDomain = resposta.toRespostaProdutos();
        respostaDomain.setId_produto(id_produto);
        respostaProdutos.registrarRespostaProduto(respostaDomain, id_produto);
        return respostaProduto(respostaDomain.getId_resposta(), id_produto);
    }

    public RespostaProdutos modificarRespostaProduto(RespostaProdutosUpdateCommand resposta, UUID id_resposta, UUID id_produto) {

        RespostaProdutos respostaDomain = resposta.toRespostaProdutos(id_resposta);
        respostaDomain.setId_produto(id_produto);
        respostaProdutos.modificarRespostaProduto(respostaDomain, id_resposta, id_produto);
        return respostaProduto(respostaDomain.getId_resposta(), id_produto);
    }

    public void deletarRespostaProduto(UUID id_resposta, UUID id_produto) {
        respostaProdutos.deletarRespostaProduto(id_resposta, id_produto);
    }

}
