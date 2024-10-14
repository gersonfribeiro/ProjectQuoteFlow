package com.workspacepi.apiquoteflow.adapters.http.enderecos;

import com.workspacepi.apiquoteflow.application.enderecos.EnderecosCreateCommand;
import com.workspacepi.apiquoteflow.application.enderecos.EnderecosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.enderecos.Enderecos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnderecosController {

    private final EnderecosHandler enderecosHandler;
    public EnderecosController(EnderecosHandler enderecosHandler) {
        this.enderecosHandler = enderecosHandler;
    }

//  Método get para a rota de cotações que devolve todas as cotações
//  (afim de testes, isso não pode ser implementado na aplicação com as cotações).

    @GetMapping("/enderecos")
    public ResponseEntity<List<Enderecos>> findAll() {
        return enderecosHandler.findAll();
    }

//  Método get para a rota de uma cotação especifica.

    @GetMapping("/enderecos/{id_endereco}")
    public ResponseEntity<Enderecos> findById(@PathVariable String id_endereco) throws Exception {
        return enderecosHandler.findById(id_endereco);
    }

    //  Método post para solicitar uma nova cotação (necessita de modificações
    @PostMapping("/enderecos")
    public ResponseEntity<Enderecos> cadastrarEndereco(@RequestBody EnderecosCreateCommand enderecosCreateCommand) throws Exception {

        return enderecosHandler.cadastrarEndereco(enderecosCreateCommand);
    }

    //  Método put para solicitar uma nova cotação (necessita de modificações
    @PutMapping("/enderecos/{id_endereco}")
    public ResponseEntity<Enderecos> modificarEndereco(@RequestBody EnderecosUpdateCommand enderecosUpdateCommand, @PathVariable String id_endereco) throws Exception {

        return enderecosHandler.modificarEndereco(enderecosUpdateCommand, id_endereco);
    }

    @DeleteMapping("enderecos/{id_endereco}")
    public ResponseEntity<String> deleteEndereco(@PathVariable String id_endereco) throws Exception {
        return enderecosHandler.deleteEnderecoById(id_endereco);
    }
}
