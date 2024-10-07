package com.workspacepi.apiquoteflow.adapters.http.cotacoes;


import com.workspacepi.apiquoteflow.application.cotacao.CotacaoCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacao.CotacaoService;
import com.workspacepi.apiquoteflow.application.cotacao.CotacaoUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacao.Cotacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

// Nosso Handler é um componente controlado pelo Spring para termos a injeção de dependência,
// sua função é chamar os Services

@Component
public class CotacaoHandler {

//    Definção do atributo dos serviços de cotação e seu construtor

    private final CotacaoService cotacaoService;
    public CotacaoHandler(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

//    Método findAll definidos nos Serviços de cotação, o retorno é uma lista de cotações

    public ResponseEntity<List<Cotacao>> findAll() {
        List<Cotacao> cotacao = cotacaoService.findAll();
        return ResponseEntity.ok(cotacao);
    }

//    Método findById definido nos Serviços de cotação, o retorno é uma cotação ou uma Exception

    public ResponseEntity<Cotacao> findById(String id_cotacao) throws Exception{
        Cotacao cotacao = cotacaoService.findById(UUID.fromString(id_cotacao));
        return ResponseEntity.ok(cotacao);
    }

    public ResponseEntity<Cotacao> solicitarCotacao(CotacaoCreateCommand cotacaoCreateCommand) throws Exception{
        Cotacao cotacao = cotacaoService.solicitarCotacao(cotacaoCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotacao);
    }

    public ResponseEntity<Cotacao> modificarCotacao(CotacaoUpdateCommand cotacaoUpdateCommand, String id_cotacao) throws Exception{
        Cotacao cotacao = cotacaoService.modificarCotacao(cotacaoUpdateCommand, UUID.fromString(id_cotacao));
        return ResponseEntity.ok(cotacao);
    }

    public ResponseEntity<String> deleteCotacaoById(String id_cotacao) throws Exception {
        cotacaoService.deleteCotacaoById(UUID.fromString(id_cotacao));
        return ResponseEntity.noContent().build();
    }
}
