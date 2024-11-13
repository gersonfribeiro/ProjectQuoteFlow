package com.workspacepi.apiquoteflow.domain.empresas;

import com.workspacepi.apiquoteflow.domain.enderecos.Enderecos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Empresas {
    private UUID id_empresa;
    private String cnpj;
    private String email;
    private String nome;
    private String telefone;
    private Enderecos endereco;

//  Construtor para modificação e inserção. Necessitade modificações para inserir usuários em uma empresa.
    public Empresas(String cnpj, String email, String nome, String telefone, Enderecos endereco) {
        this.id_empresa = UUID.randomUUID();
        this.cnpj = cnpj;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
