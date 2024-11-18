package com.workspacepi.apiquoteflow.adapters.http.respostas.produtos;

import com.workspacepi.apiquoteflow.application.respostas.produtos.RespostaProdutosCreateCommand;
import com.workspacepi.apiquoteflow.application.respostas.produtos.RespostaProdutosService;
import com.workspacepi.apiquoteflow.application.respostas.produtos.RespostaProdutosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RespostaProdutosHandler {

    private final RespostaProdutosService respostaProdutosService;

    public RespostaProdutosHandler(RespostaProdutosService respostaProdutosService) {
        this.respostaProdutosService = respostaProdutosService;
    }

    public ResponseEntity<List<RespostaProdutos>> respostaProdutos(String id_resposta) {
        List<RespostaProdutos> respostaProdutos = respostaProdutosService.respostaProdutos(UUID.fromString(id_resposta));
        return ResponseEntity.ok(respostaProdutos);
    }

    public ResponseEntity<RespostaProdutos> respostaProduto(String id_resposta,
                                                            String id_produto) {
        RespostaProdutos respostaProdutos = respostaProdutosService.respostaProduto(UUID.fromString(id_resposta), UUID.fromString(id_produto));
        return ResponseEntity.ok(respostaProdutos);
    }

    public ResponseEntity<RespostaProdutos> registrarRespostaProduto(RespostaProdutosCreateCommand createCommand,
                                                                     String id_produto) {
        RespostaProdutos respostaProdutos = respostaProdutosService.registrarRespostaProduto(createCommand, UUID.fromString(id_produto));
        return ResponseEntity.ok(respostaProdutos);
    }

    public ResponseEntity<RespostaProdutos> modificarRespostaProduto(RespostaProdutosUpdateCommand updateCommand,
                                                                     String id_resposta,
                                                                     String id_produto) {
        RespostaProdutos respostaProdutos = respostaProdutosService.modificarRespostaProduto(updateCommand, UUID.fromString(id_resposta), UUID.fromString(id_produto));
        return ResponseEntity.ok(respostaProdutos);
    }

    public ResponseEntity<String> deletarRespostaProduto(String id_resposta,
                                                         String id_produto) {
        respostaProdutosService.deletarRespostaProduto(UUID.fromString(id_resposta), UUID.fromString(id_produto));
        return ResponseEntity.noContent().build();
    }

}
