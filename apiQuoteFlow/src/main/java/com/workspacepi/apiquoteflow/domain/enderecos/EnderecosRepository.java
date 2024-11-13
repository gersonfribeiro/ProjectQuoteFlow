package com.workspacepi.apiquoteflow.domain.enderecos;

import java.util.List;
import java.util.UUID;

// Nossa interface EnderecoRepository que define a abstração dos métodos de consulta
public interface EnderecosRepository {

//  Buscar todas os endereços
    List<Enderecos> findAll();

//  Buscar por ID
    Enderecos findByEmpresa(UUID id_empresa);

//  Buscar por ID
    Enderecos findById(UUID id_endereco);

//  Inserção
    Boolean cadastrarEndereco(Enderecos enderecos);

//  Modificação
    Boolean modificarEndereco(Enderecos enderecos);

//  Delete
    Boolean deleteEnderecobyId(UUID id_endereco);

}
