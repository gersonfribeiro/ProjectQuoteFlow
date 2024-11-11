package com.workspacepi.apiquoteflow.adapters.http.cotacoes.destinatarios;

import com.workspacepi.apiquoteflow.application.cotacoes.destinatarios.DestinatariosCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.destinatarios.DestinatariosService;
import com.workspacepi.apiquoteflow.application.cotacoes.destinatarios.DestinatariosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DestinatariosHandler {
    private final DestinatariosService destinatariosService;

    public DestinatariosHandler(DestinatariosService destinatariosService) {
        this.destinatariosService = destinatariosService;
    }

    public ResponseEntity<List<Destinatarios>> findAllDestinatariosByCotacao(String id_cotacao) {
        List<Destinatarios> destinatarios = destinatariosService.findAllDestinatariosByCotacao(UUID.fromString(id_cotacao));
        return ResponseEntity.ok(destinatarios);
    }

    public ResponseEntity<Destinatarios> findProdutoByCotacaoAndId(String id_cotacao, String id_produto) {
        Destinatarios destinatarios = destinatariosService.findEmpresaDestinatariaByCotacaoAndId(UUID.fromString(id_cotacao), UUID.fromString(id_produto));
        return ResponseEntity.ok(destinatarios);
    }

    public ResponseEntity<Destinatarios> inserirDestinatario(DestinatariosCreateCommand destinatario, String id_cotacao) {
        Destinatarios destinatarios = destinatariosService.inserirDestinatario(destinatario, UUID.fromString(id_cotacao));
        return ResponseEntity.ok(destinatarios);
    }

    public ResponseEntity<Destinatarios> modificarDestinatario(DestinatariosUpdateCommand destinatario, String id_destinatario, String id_cotacao) {
        Destinatarios destinatarios = destinatariosService.modificarDestinatario(destinatario, UUID.fromString(id_destinatario), UUID.fromString(id_cotacao));
        return ResponseEntity.ok(destinatarios);
    }

    public ResponseEntity<String> removerDestinatario(String id_cotacao, String id_produto) {
        destinatariosService.removerDestinatario(UUID.fromString(id_produto), UUID.fromString(id_cotacao));
        return ResponseEntity.noContent().build();
    }

}
