package com.workspacepi.apiquoteflow.adapters.http.allErrors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.usuarios.Permissoes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

//  Propriedades especificas das exception
    @JsonProperty("id")
    @JsonIgnore
    private UUID id;

    @JsonProperty("parametro")
    @JsonIgnore
    private String parametro;

    @JsonProperty("permissao")
    private Permissoes permissao;

//  Propriedades genericas
    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("status")
    private int status;

//  Construtores para cada exception

//  Exception com UUID
    public ErrorResponse(String mensagem, UUID id, int status) {
        this.mensagem = mensagem;
        this.id = id;
        this.status = status;
    }

//  Exception com string
    public ErrorResponse(String mensagem, String parametro, int status) {
        this.mensagem = mensagem;
        this.parametro = parametro;
        this.status = status;
    }

//  Exception com Permissao
    public ErrorResponse(String mensagem, Permissoes permissao, int status) {
        this.mensagem = mensagem;
        this.permissao = permissao;
        this.status = status;
    }

    //  Exception generica
    public ErrorResponse(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

}
