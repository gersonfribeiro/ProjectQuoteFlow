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

//  Propriedades especificas das exception
    @JsonProperty("id_usuario")
    @JsonIgnore
    private UUID id_usuario;

    @JsonProperty("email")
    private String email;

//  Propriedades genericas
    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("status")
    private int status;

//  Construtores para cada exception

//  Exception de usuário não encontrado
    public ErrorResponse(String mensagem, UUID id_usuario, int status) {
        this.mensagem = mensagem;
        this.id_usuario = id_usuario;
        this.status = status;
    }

//  Exception de email já cadastrado
    public ErrorResponse(String mensagem,String email, int status) {
        this.email = email;
        this.mensagem = mensagem;
        this.status = status;
    }

//  Exception generica
    public ErrorResponse(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

}
