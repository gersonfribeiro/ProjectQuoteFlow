package com.workspacepi.apiquoteflow.adapters.http.cotacoes.error;

import java.util.UUID;

public class CotacaoErrorResponse {
    private UUID id;
    private String message;

    public CotacaoErrorResponse(UUID id, String message) {
        this.id = id;
        this.message = message;
    }
}
