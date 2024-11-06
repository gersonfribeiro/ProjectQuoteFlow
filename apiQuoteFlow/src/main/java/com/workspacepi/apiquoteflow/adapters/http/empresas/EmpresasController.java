package com.workspacepi.apiquoteflow.adapters.http.empresas;


import com.workspacepi.apiquoteflow.application.empresas.EmpresasCreateCommand;
import com.workspacepi.apiquoteflow.application.empresas.EmpresasUpdateCommand;
import com.workspacepi.apiquoteflow.domain.empresas.Empresas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresasController {

//  Definição do nosso atributo Handler assim como o seu construtor

    private final EmpresasHandler empresasHandler;
    public EmpresasController(EmpresasHandler empresasHandler) {
        this.empresasHandler = empresasHandler;
    }

//  Método get para a rota de empresa que devolve todas as empresas
//  (afim de testes, isso não pode ser implementado na aplicação com as empresas).

    @GetMapping
    public ResponseEntity<List<Empresas>> findAll() {
        return empresasHandler.findAll();
    }

//  Método get para a rota de uma empresa especifica.

    @GetMapping("/id/{id_empresa}")
    public ResponseEntity<Empresas> findById(@PathVariable String id_empresa) throws Exception {
        return empresasHandler.findById(id_empresa);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Empresas> findByCNPJ(@PathVariable String cnpj) throws Exception {
        return empresasHandler.findByCNPJ(cnpj);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Empresas> findByEmail(@PathVariable String email) throws Exception {
        return empresasHandler.findByEmail(email);
    }

    //  Método post para solicitar uma nova empresa (necessita de modificações)
    @PostMapping
    public ResponseEntity<Empresas> cadastrarEmpresa(@RequestBody EmpresasCreateCommand empresasCreateCommand) throws Exception {

        return empresasHandler.cadastrarEmpresa(empresasCreateCommand);
    }

    //  Método put para criar uma nova empresa (necessita de modificações)
    @PutMapping("/id/{id_empresa}")
    public ResponseEntity<Empresas> modificarEmpresa(@RequestBody EmpresasUpdateCommand empresasUpdateCommand, @PathVariable String id_empresa) throws Exception {

        return empresasHandler.modificarEmpresa(empresasUpdateCommand, id_empresa);
    }

    //  Método de exclusão das empresas
    @DeleteMapping("/id/{id_empresa}")
    public ResponseEntity<String> deletarEmpresa(@PathVariable String id_empresa) throws Exception {
        return empresasHandler.deleteEmpresaById(id_empresa);
    }
}
