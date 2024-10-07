package com.workspacepi.apiquoteflow.domain.enderecos;

import java.util.UUID;

//  A nossa classe Endereco que será usada na manipulação e persistência dos dados
//  atravez dos métodos crud e consultas sql feitas no código da aplicação

public class Endereco {
    private String bairro_endereco;
    private String cep_endereco;
    private String complemento_endereco;
    private String localidade_endereco;
    private String logradouro_endereco;
    private int numero_endereco;
    private String uf_endereco;
    private UUID id_empresa_endereco;
    private UUID id_endereco;

    // Construtores

    // Construtor para uso da modificação no banco de dados e RowMapper

    public Endereco(String bairro_endereco, String cep_endereco, String complemento_endereco,
            String localidade_endereco, String logradouro_endereco, int numero_endereco, String uf_endereco,
            UUID id_empresa_endereco, UUID id_endereco) {
        this.bairro_endereco = bairro_endereco;
        this.cep_endereco = cep_endereco;
        this.complemento_endereco = complemento_endereco;
        this.localidade_endereco = localidade_endereco;
        this.logradouro_endereco = logradouro_endereco;
        this.numero_endereco = numero_endereco;
        this.uf_endereco = uf_endereco;
        this.id_empresa_endereco = id_empresa_endereco;
        this.id_endereco = id_endereco;
    }

    // Construtor para o insert no banco

    public Endereco(String bairro_endereco, String cep_endereco, String complemento_endereco,
            String localidade_endereco, String logradouro_endereco, int numero_endereco, String uf_endereco,
            UUID id_empresa_endereco) {
        this.bairro_endereco = bairro_endereco;
        this.cep_endereco = cep_endereco;
        this.complemento_endereco = complemento_endereco;
        this.localidade_endereco = localidade_endereco;
        this.logradouro_endereco = logradouro_endereco;
        this.numero_endereco = numero_endereco;
        this.uf_endereco = uf_endereco;
        this.id_empresa_endereco = id_empresa_endereco;
        this.id_endereco = UUID.randomUUID();
    }

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

    public UUID getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(UUID id_endereco) {
        this.id_endereco = id_endereco;
    }
}
