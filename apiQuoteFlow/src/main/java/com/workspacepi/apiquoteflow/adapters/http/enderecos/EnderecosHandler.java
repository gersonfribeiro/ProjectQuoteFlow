package com.workspacepi.apiquoteflow.adapters.http.enderecos;

import com.workspacepi.apiquoteflow.application.enderecos.EnderecosCreateCommand;
import com.workspacepi.apiquoteflow.application.enderecos.EnderecosService;
import com.workspacepi.apiquoteflow.application.enderecos.EnderecosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.enderecos.Enderecos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EnderecosHandler {


//    Definção do atributo dos serviços de cotação e seu construtor

    private final EnderecosService enderecosService;
    public EnderecosHandler(EnderecosService enderecosService) {
        this.enderecosService = enderecosService;
    }

//    Método findAll definidos nos Serviços de cotação, o retorno é uma lista de cotações

    public ResponseEntity<List<Enderecos>> findAll() {
        List<Enderecos> enderecos = enderecosService.findAll();
        return ResponseEntity.ok(enderecos);
    }

//    Método findById definido nos Serviços de cotação, o retorno é uma cotação ou uma Exception

    public ResponseEntity<Enderecos> findById(String id_endereco) throws Exception{
        Enderecos enderecos = enderecosService.findById(UUID.fromString(id_endereco));
        return ResponseEntity.ok(enderecos);
    }

    public ResponseEntity<Enderecos> cadastrarEndereco(EnderecosCreateCommand enderecosCreateCommand) throws Exception{
        Enderecos enderecos = enderecosService.cadastrarEndereco(enderecosCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecos);
    }

    public ResponseEntity<Enderecos> modificarEndereco(EnderecosUpdateCommand enderecosUpdateCommand, String id_endereco) throws Exception{
        Enderecos enderecos = enderecosService.modificarEndereco(enderecosUpdateCommand, UUID.fromString(id_endereco));
        return ResponseEntity.ok(enderecos);
    }

    public ResponseEntity<String> deleteEnderecoById(String id_endereco) throws Exception {
        enderecosService.deletarEnderecoById(UUID.fromString(id_endereco));
        return ResponseEntity.noContent().build();
    }
}
