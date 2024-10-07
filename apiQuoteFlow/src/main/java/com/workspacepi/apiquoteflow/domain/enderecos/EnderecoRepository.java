package com.workspacepi.apiquoteflow.domain.enderecos;

import java.util.List;
import java.util.UUID;

// Nossa interface EnderecoRepository que define a abstração dos métodos de consulta
public interface EnderecoRepository {

//  Buscar todas os endereços
    List<Endereco> findAll();

//  Buscar por ID
    Endereco findById(UUID id_endereco);

//  Inserção
    Boolean cadastrarEndereco(Endereco endereco);

//  Modificação
    Boolean modificarEndereco(Endereco endereco);

//  Delete
    Boolean deleteEnderecobyId(UUID id_endereco);

}
