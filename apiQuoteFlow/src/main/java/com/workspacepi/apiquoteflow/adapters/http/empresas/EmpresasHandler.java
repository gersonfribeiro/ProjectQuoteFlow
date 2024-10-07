package com.workspacepi.apiquoteflow.adapters.http.empresas;

import com.workspacepi.apiquoteflow.application.empresas.EmpresasCreateCommand;
import com.workspacepi.apiquoteflow.application.empresas.EmpresasService;
import com.workspacepi.apiquoteflow.application.empresas.EmpresasUpdateCommand;
import com.workspacepi.apiquoteflow.domain.empresas.Empresa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmpresasHandler {

//    Definção do atributo dos serviços de empresas e seu construtor

    private final EmpresasService empresasService;
    public EmpresasHandler(EmpresasService empresasService) {
        this.empresasService = empresasService;
    }

//    Método findAll definidos nos Serviços de cotação, o retorno é uma lista de cotações

    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> empresas = empresasService.findAll();
        return ResponseEntity.ok(empresas);
    }

//    Método findById definido nos Serviços de cotação, o retorno é uma cotação ou uma Exception

    public ResponseEntity<Empresa> findById(String id_empresa) throws Exception{
        Empresa empresa = empresasService.findById(UUID.fromString(id_empresa));
        return ResponseEntity.ok(empresa);
    }

    public ResponseEntity<Empresa> cadastrarEmpresa(EmpresasCreateCommand empresasCreateCommand) throws Exception{
        Empresa empresa = empresasService.cadastrarEmpresa(empresasCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
    }

    public ResponseEntity<Empresa> modificarEmpresa(EmpresasUpdateCommand empresasUpdateCommand, String id_empresa) throws Exception{
        Empresa empresa = empresasService.modificarEmpresa(empresasUpdateCommand, UUID.fromString(id_empresa));
        return ResponseEntity.ok(empresa);
    }

    public ResponseEntity<String> deleteEmpresaById(String id_empresa) throws Exception {
        empresasService.deletarEmpresaById(UUID.fromString(id_empresa));
        return ResponseEntity.noContent().build();
    }
}
