package com.workspacepi.apiquoteflow.adapters.http.enderecos;

import com.workspacepi.apiquoteflow.application.enderecos.EnderecosCreateCommand;
import com.workspacepi.apiquoteflow.application.enderecos.EnderecosService;
import com.workspacepi.apiquoteflow.application.enderecos.EnderecosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.enderecos.Endereco;
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

    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> enderecos = enderecosService.findAll();
        return ResponseEntity.ok(enderecos);
    }

//    Método findById definido nos Serviços de cotação, o retorno é uma cotação ou uma Exception

    public ResponseEntity<Endereco> findById(String id_endereco) throws Exception{
        Endereco endereco = enderecosService.findById(UUID.fromString(id_endereco));
        return ResponseEntity.ok(endereco);
    }

    public ResponseEntity<Endereco> cadastrarEndereco(EnderecosCreateCommand enderecosCreateCommand) throws Exception{
        Endereco endereco = enderecosService.cadastrarEndereco(enderecosCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    public ResponseEntity<Endereco> modificarEndereco(EnderecosUpdateCommand enderecosUpdateCommand, String id_endereco) throws Exception{
        Endereco endereco = enderecosService.modificarEndereco(enderecosUpdateCommand, UUID.fromString(id_endereco));
        return ResponseEntity.ok(endereco);
    }

    public ResponseEntity<String> deleteEnderecoById(String id_endereco) throws Exception {
        enderecosService.deletarEnderecoById(UUID.fromString(id_endereco));
        return ResponseEntity.noContent().build();
    }
}
