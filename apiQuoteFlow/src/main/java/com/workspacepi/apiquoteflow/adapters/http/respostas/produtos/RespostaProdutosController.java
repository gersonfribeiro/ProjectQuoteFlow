package com.workspacepi.apiquoteflow.adapters.http.respostas.produtos;

import com.workspacepi.apiquoteflow.application.respostas.produtos.RespostaProdutosCreateCommand;
import com.workspacepi.apiquoteflow.application.respostas.produtos.RespostaProdutosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos/{id_resposta}")
public class RespostaProdutosController {
    private final RespostaProdutosHandler respostaProdutosHandler;

    public RespostaProdutosController(RespostaProdutosHandler respostaProdutosHandler) {
        this.respostaProdutosHandler = respostaProdutosHandler;
    }

    @GetMapping
    public ResponseEntity<List<RespostaProdutos>> respostaProdutos(@PathVariable String id_resposta) {
        return ResponseEntity.ok(respostaProdutosHandler.respostaProdutos(id_resposta).getBody());
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<RespostaProdutos> respostaProduto(@PathVariable String id_resposta,
                                                            @PathVariable String id_produto) {
        return ResponseEntity.ok(respostaProdutosHandler.respostaProduto(id_resposta, id_produto).getBody());
    }

    @PostMapping("/{id_produto}")
    public ResponseEntity<RespostaProdutos> registrarRespostaProduto(@RequestBody RespostaProdutosCreateCommand createCommand,
                                                                     @PathVariable String id_produto) {
        return ResponseEntity.ok(respostaProdutosHandler.registrarRespostaProduto(createCommand, id_produto).getBody());
    }

    @PutMapping("/{id_produto}")
    public ResponseEntity<RespostaProdutos> modificarRespostaProduto(@RequestBody RespostaProdutosUpdateCommand updateCommand,
                                                                     @PathVariable String id_resposta,
                                                                     @PathVariable String id_produto) {
        return ResponseEntity.ok(respostaProdutosHandler.modificarRespostaProduto(updateCommand, id_resposta, id_produto).getBody());
    }

    @DeleteMapping("/{id_produto}")
    public ResponseEntity<String> deletarRespostaProduto(@PathVariable String id_resposta,
                                                         @PathVariable String id_produto) {
        return respostaProdutosHandler.deletarRespostaProduto(id_resposta, id_produto);
    }
}
