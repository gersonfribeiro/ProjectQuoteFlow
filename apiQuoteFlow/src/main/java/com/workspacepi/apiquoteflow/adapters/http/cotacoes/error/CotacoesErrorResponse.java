package com.workspacepi.apiquoteflow.adapters.http.cotacoes.error;

import java.util.UUID;

public class CotacoesErrorResponse {
    private UUID id;
    private String message;

    public CotacoesErrorResponse(UUID id, String message) {
        this.id = id;
        this.message = message;
    }
}
