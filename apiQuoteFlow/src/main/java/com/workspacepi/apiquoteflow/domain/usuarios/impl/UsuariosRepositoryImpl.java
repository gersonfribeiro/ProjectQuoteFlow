package com.workspacepi.apiquoteflow.domain.usuarios.impl;

import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import com.workspacepi.apiquoteflow.domain.usuarios.UsuariosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuariosRepositoryImpl implements UsuariosRepository {

    // Simulação de um banco de dados em memória
    private final List<Usuarios> usuariosDatabase = new ArrayList<>();

    @Override
    public List<Usuarios> findAll() {
        return usuariosDatabase;
    }

    @Override
    public Usuarios findById(UUID id_usuario) {
        // Retorna o usuário se encontrado, senão retorna null
        return usuariosDatabase.stream()
                .filter(usuario -> usuario.getId_usuario().equals(id_usuario))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean cadastrarUsuario(Usuarios usuario) {
        // Adiciona o novo usuário à lista
        return usuariosDatabase.add(usuario);
    }

    @Override
    public Boolean modificarUsuario(Usuarios usuario) {
        // Encontra o usuário pelo ID e modifica seus dados
        for (int i = 0; i < usuariosDatabase.size(); i++) {
            if (usuariosDatabase.get(i).getId_usuario().equals(usuario.getId_usuario())) {
                usuariosDatabase.set(i, usuario);
                return true;
            }
        }
        return false; // Se o usuário não foi encontrado, retorna false
    }

    @Override
    public Boolean deleteUsuarioById(UUID id_usuario) {
        // Remove o usuário pelo ID
        return usuariosDatabase.removeIf(usuario -> usuario.getId_usuario().equals(id_usuario));
    }
}