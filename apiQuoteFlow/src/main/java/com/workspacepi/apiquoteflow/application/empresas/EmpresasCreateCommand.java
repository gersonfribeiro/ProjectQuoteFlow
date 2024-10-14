package com.workspacepi.apiquoteflow.application.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.empresas.Empresas;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpresasCreateCommand {

    @JsonProperty("cnpj")
    private String cnpj_empresa;

    @JsonProperty("email")
    private String email_empresa;

    @JsonProperty("nome")
    private String nome_empresa;

    @JsonProperty("senha")
    private String senha_empresa;

    //  Conversão para usuario
    public Empresas toEmpresa() {
        return new Empresas(cnpj_empresa, email_empresa, nome_empresa, senha_empresa);
    }

}
