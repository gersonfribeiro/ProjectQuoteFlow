package com.workspacepi.apiquoteflow.adapters.http.cotacoes;


import com.workspacepi.apiquoteflow.application.cotacoes.CotacoesCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.CotacoesService;
import com.workspacepi.apiquoteflow.application.cotacoes.CotacoesUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.Cotacoes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

// Nosso Handler é um componente controlado pelo Spring para termos a injeção de dependência,
// sua função é chamar os Services

@Component
public class CotacoesHandler {

//    Definção do atributo dos serviços de cotação e seu construtor

    private final CotacoesService cotacoesService;
    public CotacoesHandler(CotacoesService cotacoesService) {
        this.cotacoesService = cotacoesService;
    }

//    Método findAll definidos nos Serviços de cotação, o retorno é uma lista de cotações

    public ResponseEntity<List<Cotacoes>> findAll() {
        List<Cotacoes> cotacoes = cotacoesService.findAll();
        return ResponseEntity.ok(cotacoes);
    }

//    Método findById definido nos Serviços de cotação, o retorno é uma cotação ou uma Exception

    public ResponseEntity<Cotacoes> findById(String id_cotacao) throws Exception{
        Cotacoes cotacoes = cotacoesService.findById(UUID.fromString(id_cotacao));
        return ResponseEntity.ok(cotacoes);
    }

    public ResponseEntity<Cotacoes> solicitarCotacao(CotacoesCreateCommand cotacoesCreateCommand) throws Exception{
        Cotacoes cotacoes = cotacoesService.solicitarCotacao(cotacoesCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotacoes);
    }

    public ResponseEntity<Cotacoes> modificarCotacao(CotacoesUpdateCommand cotacoesUpdateCommand, String id_cotacao) throws Exception{
        Cotacoes cotacoes = cotacoesService.modificarCotacao(cotacoesUpdateCommand, UUID.fromString(id_cotacao));
        return ResponseEntity.ok(cotacoes);
    }

    public ResponseEntity<String> deleteCotacaoById(String id_cotacao) throws Exception {
        cotacoesService.deleteCotacaoById(UUID.fromString(id_cotacao));
        return ResponseEntity.noContent().build();
    }
}
