package com.workspacepi.apiquoteflow.domain.empresas;

import java.util.UUID;

public class Empresa {
    private UUID id_empresa;
    private String cnpj_empresa;
    private String email_empresa;
    private String nome_empresa;
    private String senha_empresa;

//  Construtor do RowMapper.
    public Empresa(UUID id_empresa, String cnpj_empresa, String email_empresa, String nome_empresa, String senha_empresa) {
        this.id_empresa = id_empresa;
        this.cnpj_empresa = cnpj_empresa;
        this.email_empresa = email_empresa;
        this.nome_empresa = nome_empresa;
        this.senha_empresa = senha_empresa;
    }

//  Construtor para modificação e inserção. Necessitade modificações para inserir usuários em uma empresa.
    public Empresa(String cnpj_empresa, String email_empresa, String nome_empresa, String senha_empresa) {
        this.id_empresa = UUID.randomUUID();
        this.cnpj_empresa = cnpj_empresa;
        this.email_empresa = email_empresa;
        this.nome_empresa = nome_empresa;
        this.senha_empresa = senha_empresa;
    }

    public UUID getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(UUID id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getCnpj_empresa() {
        return cnpj_empresa;
    }

    public void setCnpj_empresa(String cnpj_empresa) {
        this.cnpj_empresa = cnpj_empresa;
    }

    public String getEmail_empresa() {
        return email_empresa;
    }

    public void setEmail_empresa(String email_empresa) {
        this.email_empresa = email_empresa;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getSenha_empresa() {
        return senha_empresa;
    }

    public void setSenha_empresa(String senha_empresa) {
        this.senha_empresa = senha_empresa;
    }
}
