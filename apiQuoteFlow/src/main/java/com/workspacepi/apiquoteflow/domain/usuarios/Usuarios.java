package com.workspacepi.apiquoteflow.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Usuarios {
    private UUID id_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private String telefone_usuario;
    private UUID id_empresa_usuario;

//  Construtor para inserção no banco de dados
    public Usuarios(String nome_usuario, String email_usuario, String senha_usuario, String telefone_usuario, UUID id_empresa_usuario) {
        this.id_usuario = UUID.randomUUID();
        this.nome_usuario = nome_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
        this.telefone_usuario = telefone_usuario;
        this.id_empresa_usuario = id_empresa_usuario;
    }

}
