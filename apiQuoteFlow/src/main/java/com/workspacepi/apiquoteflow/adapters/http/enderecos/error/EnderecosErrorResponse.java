package com.workspacepi.apiquoteflow.adapters.http.enderecos.error;

import java.util.UUID;

public class EnderecosErrorResponse {
    private UUID id;
    private String message;

    public EnderecosErrorResponse(UUID id, String message) {
        this.id = id;
        this.message = message;
    }
}
