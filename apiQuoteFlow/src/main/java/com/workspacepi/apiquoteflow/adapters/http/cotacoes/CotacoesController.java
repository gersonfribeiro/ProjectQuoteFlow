package com.workspacepi.apiquoteflow.adapters.http.cotacoes;

import com.workspacepi.apiquoteflow.application.cotacoes.CotacoesCreateCommand;
import com.workspacepi.apiquoteflow.application.cotacoes.CotacoesUpdateCommand;
import com.workspacepi.apiquoteflow.domain.cotacoes.Cotacoes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//  Esse é o nosso controller, ele define os casos de uso para uma rota de cotacoes usando o método get

@RestController
public class CotacoesController {

//  Definição do nosso atributo Handles assim como o seu construtor

    private final CotacoesHandler cotacoesHandler;
    public CotacoesController(CotacoesHandler cotacoesHandler) {
        this.cotacoesHandler = cotacoesHandler;
    }

//  Método get para a rota de cotações que devolve todas as cotações
//  (afim de testes, isso não pode ser implementado na aplicação com as cotações).

    @GetMapping("/cotacoes")
    public ResponseEntity<List<Cotacoes>> findAll() {
        return cotacoesHandler.findAll();
    }

//  Método get para a rota de uma cotação especifica.

    @GetMapping("/cotacoes/{id_cotacao}")
    public ResponseEntity<Cotacoes> findById(@PathVariable String id_cotacao) throws Exception {
        return cotacoesHandler.findById(id_cotacao);
    }

//  Método post para solicitar uma nova cotação (necessita de modificações
    @PostMapping("/cotacoes")
    public ResponseEntity<Cotacoes> solicitarCotacao(@RequestBody CotacoesCreateCommand cotacoesCreateCommand) throws Exception {

        return cotacoesHandler.solicitarCotacao(cotacoesCreateCommand);
    }

//  Método put para solicitar uma nova cotação (necessita de modificações
    @PutMapping("/cotacoes/{id_cotacao}")
    public ResponseEntity<Cotacoes> modificarCotacao(@RequestBody CotacoesUpdateCommand cotacoesUpdateCommandCommand, @PathVariable String id_cotacao) throws Exception {

        return cotacoesHandler.modificarCotacao(cotacoesUpdateCommandCommand, id_cotacao);
    }

    @DeleteMapping("/cotacoes/{id_cotacao}")
    public ResponseEntity<String> deleteCotacao(@PathVariable String id_cotacao) throws Exception {
        return cotacoesHandler.deleteCotacaoById(id_cotacao);
    }

}
