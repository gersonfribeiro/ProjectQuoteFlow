package com.workspacepi.apiquoteflow.adapters.http.respostas;

import com.workspacepi.apiquoteflow.application.respostas.RespostaCotacaoCreateCommand;
import com.workspacepi.apiquoteflow.application.respostas.RespostaCotacaoUpdateCommand;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{id_empresa_resposta}")
public class RespostaCotacaoController {
    
    private final RespostaCotacaoHandler respostaCotacaoHandler;

    public RespostaCotacaoController(RespostaCotacaoHandler respostaCotacaoHandler) {
        this.respostaCotacaoHandler = respostaCotacaoHandler;
    }

    @GetMapping
    public ResponseEntity<List<RespostaCotacao>> respostasCotacao(@PathVariable String id_empresa_resposta) {
        return respostaCotacaoHandler.respostasCotacao(id_empresa_resposta);
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<RespostaCotacao> respostasCotacao(@PathVariable String id_empresa_resposta,
                                                            @PathVariable String id_produto) {
        return respostaCotacaoHandler.respostaCotacao(id_empresa_resposta, id_produto);
    }

    @PostMapping("/{id_cotacao}")
    public ResponseEntity<RespostaCotacao> registrarResposta(@RequestBody RespostaCotacaoCreateCommand resposta,
                                                            @PathVariable String id_empresa_resposta,
                                                            @PathVariable String id_cotacao) {
        return respostaCotacaoHandler.registrarResposta(resposta, id_empresa_resposta, id_cotacao);
    }

    @PutMapping("{id_cotacao}")
    public ResponseEntity<RespostaCotacao> modificarResposta(@RequestBody RespostaCotacaoUpdateCommand resposta,
                                                             @PathVariable String id_empresa_resposta,
                                                             @PathVariable String id_cotacao) {
        return respostaCotacaoHandler.modificarResposta(resposta, id_empresa_resposta, id_cotacao);
    }

    @DeleteMapping("/{id_cotacao}")
    public ResponseEntity<String> deletarResposta(@PathVariable String id_empresa_resposta,
                                                           @PathVariable String id_cotacao) {
        return respostaCotacaoHandler.deletarResposta(id_empresa_resposta, id_cotacao);
    }
}
