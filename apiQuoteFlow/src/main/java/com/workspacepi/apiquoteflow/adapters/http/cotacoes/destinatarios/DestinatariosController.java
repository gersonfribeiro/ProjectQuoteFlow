package com.workspacepi.apiquoteflow.adapters.http.cotacoes.destinatarios;

import com.workspacepi.apiquoteflow.application.cotacoes.destinatarios.DestinatariosCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.destinatarios.DestinatariosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id_cotacao}/destinatarios")
public class DestinatariosController {

    private final DestinatariosHandler destinatariosHandler;

    public DestinatariosController(DestinatariosHandler destinatariosHandler) {
        this.destinatariosHandler = destinatariosHandler;
    }

    @GetMapping
    public ResponseEntity<List<Destinatarios>> findAllDestinatariosByCotacao(@PathVariable String id_cotacao) {
        return destinatariosHandler.findAllDestinatariosByCotacao(id_cotacao);
    }

    @GetMapping("/{id_destinatario}")
    public ResponseEntity<Destinatarios> findProdutoByCotacaoAndId(@PathVariable String id_cotacao, @PathVariable String id_destinatario) {
        return destinatariosHandler.findProdutoByCotacaoAndId(id_cotacao, id_destinatario);
    }

    @PostMapping
    public ResponseEntity<List<Destinatarios>> inserirDestinatario(@RequestBody List<DestinatariosCreateCommand> destinatarios, @PathVariable String id_cotacao) {
        return destinatariosHandler.inserirDestinatarios(destinatarios, id_cotacao);
    }

    @PutMapping("/{id_destinatario}")
    public ResponseEntity<Destinatarios> modificarDestinatario(@RequestBody DestinatariosUpdateCommand destinatario, @PathVariable String id_cotacao, @PathVariable String id_destinatario) {
        return destinatariosHandler.modificarDestinatario(destinatario, id_destinatario, id_cotacao);
    }

    @DeleteMapping("/{id_destinatario}")
    public ResponseEntity<String> removerDestinatario(@PathVariable String id_cotacao, String id_destinatario) {
        return destinatariosHandler.removerDestinatario(id_cotacao, id_destinatario);
    }
}
