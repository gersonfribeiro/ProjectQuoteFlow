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
public class Enderecos {
    private String bairro;
    private String cep;
    private String complemento;
    private String localidade;
    private String logradouro;
    private int numero;
    private String uf;
    private UUID id_empresa;
    private UUID id_endereco;

    // Construtores

    // Construtor para o insert no banco

    public Enderecos(String bairro, String cep, String complemento,
                     String localidade, String logradouro, int numero, String uf,
                     UUID id_empresa) {
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.localidade = localidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.uf = uf;
        this.id_empresa = id_empresa;
        this.id_endereco = UUID.randomUUID();
    }

}
