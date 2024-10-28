package com.workspacepi.apiquoteflow.application.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.usuarios.Permissoes;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//  Usando Jackson para serialização
@Getter
@Setter
public class UsuariosCreateCommand {

    @JsonProperty("nome")
    private String nome_usuario;

    @JsonProperty("email")
    private String email_usuario;

    @JsonProperty("senha")
    private String senha_usuario;

    @JsonProperty("telefone")
    private String telefone_usuario;

    @JsonProperty("id_empresa")
    private UUID id_empresa_usuario;

    @JsonProperty("permissao")
    private Permissoes permissao;

//  Conversão para usuario
    public Usuarios toUsuario() {
        return new Usuarios(nome_usuario, email_usuario, senha_usuario, telefone_usuario, id_empresa_usuario);
    }

}
