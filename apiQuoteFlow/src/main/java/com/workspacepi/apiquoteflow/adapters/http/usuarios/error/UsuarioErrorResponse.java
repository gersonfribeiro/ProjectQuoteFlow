package com.workspacepi.apiquoteflow.adapters.http.usuarios.error;

import java.util.UUID;

public class UsuarioErrorResponse {
    private UUID id;
    private String message;

    public UsuarioErrorResponse(UUID id, String message) {
        this.id = id;
        this.message = message;
    }
}
