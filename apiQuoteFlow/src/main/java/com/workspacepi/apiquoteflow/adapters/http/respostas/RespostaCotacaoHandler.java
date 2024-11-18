package com.workspacepi.apiquoteflow.adapters.http.respostas;

import com.workspacepi.apiquoteflow.application.respostas.CotacaoComProdutosDTO;
import com.workspacepi.apiquoteflow.application.respostas.RespostaCotacaoCreateCommand;
import com.workspacepi.apiquoteflow.application.respostas.RespostaCotacaoService;
import com.workspacepi.apiquoteflow.application.respostas.RespostaCotacaoUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RespostaCotacaoHandler {

    private final RespostaCotacaoService respostaCotacaoService;

    public RespostaCotacaoHandler(RespostaCotacaoService respostaCotacaoService) {
        this.respostaCotacaoService = respostaCotacaoService;
    }

    public ResponseEntity<List<RespostaCotacao>> respostasCotacao(String id_empresa_resposta) {
        List<RespostaCotacao> respostas = respostaCotacaoService.respostasCotacao(UUID.fromString(id_empresa_resposta));
        return ResponseEntity.ok(respostas);
    }

    public ResponseEntity<List<CotacaoComProdutosDTO>> buscarCotacoesComProdutos(String id_empresa_resposta) {
        List<CotacaoComProdutosDTO> respostas = respostaCotacaoService.buscarCotacoesComProdutos(UUID.fromString(id_empresa_resposta));
        return ResponseEntity.ok(respostas);
    }

    public ResponseEntity<RespostaCotacao> respostaCotacao(String id_empresa_resposta, String id_cotacao) {
        RespostaCotacao resposta = respostaCotacaoService.respostaCotacao(UUID.fromString(id_empresa_resposta), UUID.fromString(id_cotacao));
        return ResponseEntity.ok(resposta);
    }

    public ResponseEntity<RespostaCotacao> registrarResposta(RespostaCotacaoCreateCommand resposta, String id_empresa_resposta, String id_cotacao) {
        RespostaCotacao respostas = respostaCotacaoService.registrarResposta(resposta, UUID.fromString(id_empresa_resposta), UUID.fromString(id_cotacao));
        return ResponseEntity.ok(respostas);
    }

    public ResponseEntity<RespostaCotacao> modificarResposta(RespostaCotacaoUpdateCommand resposta, String id_empresa_resposta, String id_cotacao) {
        RespostaCotacao respostas = respostaCotacaoService.modificarResposta(resposta, UUID.fromString(id_empresa_resposta), UUID.fromString(id_cotacao));
        return ResponseEntity.ok(respostas);
    }

    public ResponseEntity<String> deletarResposta(String id_empresa_resposta, String id_cotacao) {
        respostaCotacaoService.deletarResposta(UUID.fromString(id_empresa_resposta), UUID.fromString(id_cotacao));
        return ResponseEntity.noContent().build();
    }
}
