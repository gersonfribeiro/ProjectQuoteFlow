package com.workspacepi.apiquoteflow.domain.empresas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Empresa {
    private UUID id_empresa;
    private String cnpj_empresa;
    private String email_empresa;
    private String nome_empresa;
    private String senha_empresa;

//  Construtor para modificação e inserção. Necessitade modificações para inserir usuários em uma empresa.
    public Empresa(String cnpj_empresa, String email_empresa, String nome_empresa, String senha_empresa) {
        this.id_empresa = UUID.randomUUID();
        this.cnpj_empresa = cnpj_empresa;
        this.email_empresa = email_empresa;
        this.nome_empresa = nome_empresa;
        this.senha_empresa = senha_empresa;
    }

}
