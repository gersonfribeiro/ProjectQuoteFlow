package com.workspacepi.apiquoteflow.application.enderecos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.enderecos.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//  Usando Jackson para serialização
@Setter
@Getter
public class EnderecosCreateCommand {

    @JsonProperty("bairro")
    private String bairro_endereco;

    @JsonProperty("cep")
    private String cep_endereco;

    @JsonProperty("complemento")
    private String complemento_endereco;

    @JsonProperty("localidade")
    private String localidade_endereco;

    @JsonProperty("logradouro")
    private String logradouro_endereco;

    @JsonProperty("numero")
    private int numero_endereco;

    @JsonProperty("uf")
    private String uf_endereco;

    @JsonProperty("id_empresa")
    private UUID id_empresa_endereco;

//  Conversão para endereco
    public Endereco toEndereco() {
        return new Endereco(bairro_endereco, cep_endereco, complemento_endereco, localidade_endereco,
                logradouro_endereco, numero_endereco, uf_endereco, id_empresa_endereco);
    }

}
