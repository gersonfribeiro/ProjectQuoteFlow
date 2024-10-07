package com.workspacepi.apiquoteflow.domain.usuarios;

import java.util.List;
import java.util.UUID;

public interface UsuariosRepository {

//  Buscar todos os usuários
    List<Usuarios> findAll();

//  Buscar usuário por ID
    Usuarios findById(UUID id_usuario);

//  Inserção
    Boolean cadastrarUsuario(Usuarios usuario);

//  Modificação
    Boolean modificarUsuario(Usuarios usuario);

//  Delete
    Boolean deleteUsuarioById(UUID id_usuario);

}
