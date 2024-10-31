package com.workspacepi.apiquoteflow.adapters.http.allErrors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorResponse {

    @JsonProperty("id_usuario")
    @JsonIgnore
    private UUID id_usuario;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("status")
    private int status;

    public ErrorResponse(String mensagem, UUID id_usuario, int status) {
        this.mensagem = mensagem;
        this.id_usuario = id_usuario;
        this.status = status;
    }

    public ErrorResponse(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

}
