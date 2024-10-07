package com.workspacepi.apiquoteflow.application.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;

import java.util.UUID;

//  Usando Jackson para serialização
public class UsuariosUpdateCommand {

    @JsonProperty("nome_usuario")
    private String nome_usuario;

    @JsonProperty("email_usuario")
    private String email_usuario;

    @JsonProperty("senha_usuario")
    private String senha_usuario;

    @JsonProperty("telefone_usuario")
    private String telefone_usuario;

    @JsonProperty("id_empresa_usuario")
    private UUID id_empresa_usuario;

//  Conversão para usuario
    public Usuarios toUsuario(UUID id_usuario) { return new Usuarios(id_usuario, nome_usuario, email_usuario, senha_usuario, telefone_usuario, id_empresa_usuario);}

//  Getters e setters
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
