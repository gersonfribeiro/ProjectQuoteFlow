package com.workspacepi.apiquoteflow.application.enderecos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.enderecos.Endereco;

import java.util.UUID;

//  Usando Jackson para serialização
public class EnderecosCreateCommand {

    @JsonProperty("bairro_endereco")
    private String bairro_endereco;

    @JsonProperty("cep_endereco")
    private String cep_endereco;

    @JsonProperty("complemento_endereco")
    private String complemento_endereco;

    @JsonProperty("localidade_endereco")
    private String localidade_endereco;

    @JsonProperty("logradouro_endereco")
    private String logradouro_endereco;

    @JsonProperty("nuero_endereco")
    private int numero_endereco;

    @JsonProperty("uf_endereco")
    private String uf_endereco;

    @JsonProperty("id_empresa_endereco")
    private UUID id_empresa_endereco;

//  Conversão para endereco
    public Endereco toEndereco() {
        return new Endereco(bairro_endereco, cep_endereco, complemento_endereco, localidade_endereco, logradouro_endereco, numero_endereco, uf_endereco, id_empresa_endereco);
    }

//  Getters e setters

    public String getBairro_endereco() {
        return bairro_endereco;
    }

    public void setBairro_endereco(String bairro_endereco) {
        this.bairro_endereco = bairro_endereco;
    }

    public String getCep_endereco() {
        return cep_endereco;
    }

    public void setCep_endereco(String cep_endereco) {
        this.cep_endereco = cep_endereco;
    }

    public String getComplemento_endereco() {
        return complemento_endereco;
    }

    public void setComplemento_endereco(String complemento_endereco) {
        this.complemento_endereco = complemento_endereco;
    }

    public String getLocalidade_endereco() {
        return localidade_endereco;
    }

    public void setLocalidade_endereco(String localidade_endereco) {
        this.localidade_endereco = localidade_endereco;
    }

    public String getLogradouro_endereco() {
        return logradouro_endereco;
    }

    public void setLogradouro_endereco(String logradouro_endereco) {
        this.logradouro_endereco = logradouro_endereco;
    }

    public int getNumero_endereco() {
        return numero_endereco;
    }

    public void setNumero_endereco(int numero_endereco) {
        this.numero_endereco = numero_endereco;
    }

    public String getUf_endereco() {
        return uf_endereco;
    }

    public void setUf_endereco(String uf_endereco) {
        this.uf_endereco = uf_endereco;
    }

    public UUID getId_empresa_endereco() {
        return id_empresa_endereco;
    }

    public void setId_empresa_endereco(UUID id_empresa_endereco) {
        this.id_empresa_endereco = id_empresa_endereco;
    }

}
