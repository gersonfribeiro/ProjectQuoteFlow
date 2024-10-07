package com.workspacepi.apiquoteflow.domain.empresas;


import java.util.List;
import java.util.UUID;

public interface EmpresaRepository {

//  Buscar todas as empresas
    List<Empresa> findAll();

//  Buscar empresa por ID
    Empresa findById(UUID id_empresa);

//  Inserção
    Boolean cadastrarEmpresa(Empresa empresa);

//  Modificação
    Boolean modificarEmpresa(Empresa empresa);

//  Delete
    Boolean deleteEmpresaById(UUID id_empresa);

}
