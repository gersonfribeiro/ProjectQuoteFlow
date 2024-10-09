package com.workspacepi.apiquoteflow.application.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.empresas.Empresa;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class EmpresasUpdateCommand {

    @JsonProperty("cnpj")
    private String cnpj_empresa;

    @JsonProperty("email")
    private String email_empresa;

    @JsonProperty("nome")
    private String nome_empresa;

    @JsonProperty("senha")
    private String senha_empresa;

    //  Conversão para usuario
    public Empresa toEmpresa(UUID id_empresa) {
        return new Empresa(id_empresa, cnpj_empresa, email_empresa, nome_empresa, senha_empresa);
    }

}
