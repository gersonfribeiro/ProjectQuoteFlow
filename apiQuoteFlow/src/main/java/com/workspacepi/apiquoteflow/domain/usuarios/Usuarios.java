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
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private UUID id_empresa;

    // Construtor para inserção no banco de dados
    public Usuarios(String nome, String email, String senha, String telefone, UUID id_empresa) {
        this.id_usuario = UUID.randomUUID();
        this.id_usuario = UUID.randomUUID(); // Gera um UUID aleatório
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.id_empresa = id_empresa;

    }
}
