package com.workspacepi.apiquoteflow.application.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.empresas.Empresa;

public class EmpresasCreateCommand {

    @JsonProperty("cnpj_empresa")
    private String cnpj_empresa;

    @JsonProperty("email_empresa")
    private String email_empresa;

    @JsonProperty("nome_empresa")
    private String nome_empresa;

    @JsonProperty("senha_empresa")
    private String senha_empresa;

    //  Conversão para usuario
    public Empresa toEmpresa() {
        return new Empresa(cnpj_empresa, email_empresa, nome_empresa, senha_empresa);
    }

//  Getters e setters

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
