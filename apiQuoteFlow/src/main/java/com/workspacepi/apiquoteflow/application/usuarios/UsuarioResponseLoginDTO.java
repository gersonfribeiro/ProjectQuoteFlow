package com.workspacepi.apiquoteflow.application.usuarios;

import java.util.UUID;

public record UsuarioResponseLoginDTO(UUID id_usuario, String token) {  }
