package com.workspacepi.apiquoteflow.application.usuarios;

import com.workspacepi.apiquoteflow.domain.usuarios.Permissoes;

public record UsuarioRegisterDTO(String email, String password, Permissoes permissao) {
}
