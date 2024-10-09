package com.workspacepi.apiquoteflow.domain.enderecos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//  A nossa classe Endereco que será usada na manipulação e persistência dos dados
//  atravez dos métodos crud e consultas sql feitas no código da aplicação

@Getter
@Setter
@AllArgsConstructor
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

}
