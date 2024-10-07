package com.workspacepi.apiquoteflow.domain.usuarios;

import java.util.UUID;

public class Usuarios {
    private UUID id_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private String telefone_usuario;
    private UUID id_empresa_usuario;

//  Construtor para uso do RowMapper
    public Usuarios(UUID id_usuario, String nome_usuario, String email_usuario, String senha_usuario, String telefone_usuario, UUID id_empresa_usuario) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
        this.telefone_usuario = telefone_usuario;
        this.id_empresa_usuario = id_empresa_usuario;
    }

//  Construtor para inserção no banco de dados
    public Usuarios(String nome_usuario, String email_usuario, String senha_usuario, String telefone_usuario, UUID id_empresa_usuario) {
        this.id_usuario = UUID.randomUUID();
        this.nome_usuario = nome_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
        this.telefone_usuario = telefone_usuario;
        this.id_empresa_usuario = id_empresa_usuario;
    }

    public UUID getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UUID id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getTelefone_usuario() {
        return telefone_usuario;
    }

    public void setTelefone_usuario(String telefone_usuario) {
        this.telefone_usuario = telefone_usuario;
    }

    public UUID getId_empresa_usuario() {
        return id_empresa_usuario;
    }

    public void setId_empresa_usuario(UUID id_empresa_usuario) {
        this.id_empresa_usuario = id_empresa_usuario;
    }
}
