package com.workspacepi.apiquoteflow.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Usuarios {
    private UUID id_usuario;
    private String nome;
    private String email;
    private String senha;
<<<<<<< HEAD
    private String telefone;
    private UUID id_empresa;

//  Construtor para inserção no banco de dados
    public Usuarios(String nome, String email, String senha, String telefone, UUID id_empresa) {
=======
    private String telefone_usuario;
    private UUID id_empresa_usuario;

//  Construtor para inserção no banco de dados
    public Usuarios(String nome, String email, String senha, String telefone_usuario, UUID id_empresa_usuario) {
>>>>>>> cdccfd2c604ef4a1ace5a90f68f6217f90ec919b
        this.id_usuario = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
<<<<<<< HEAD
        this.telefone = telefone;
        this.id_empresa = id_empresa;
=======
        this.telefone_usuario = telefone_usuario;
        this.id_empresa_usuario = id_empresa_usuario;
>>>>>>> cdccfd2c604ef4a1ace5a90f68f6217f90ec919b
    }

}
