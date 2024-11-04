package com.workspacepi.apiquoteflow.application.empresas;

import com.workspacepi.apiquoteflow.application.empresas.exceptions.*;
import com.workspacepi.apiquoteflow.domain.empresas.Empresas;
import com.workspacepi.apiquoteflow.domain.empresas.EmpresasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresasService {

    private final EmpresasRepository empresasRepository;

    public EmpresasService(EmpresasRepository empresasRepository) {
        this.empresasRepository = empresasRepository;
    }

    public List<Empresas> findAll() { return empresasRepository.findAll(); }

    public Empresas findById(UUID id_empresa) throws Exception {
        Empresas empresas = empresasRepository.findById(id_empresa);

        if (empresas == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        return empresas;
    }

    public Empresas findByCNPJ(String cnpj) throws Exception {
        Empresas empresas = empresasRepository.findByCNPJ(cnpj);

        if (empresas == null)
            throw new EmpresaCNPJNaoEncontradoException(cnpj);

        return empresas;
    }

    public Empresas findByEmail(String email) throws Exception {
        Empresas empresas = empresasRepository.findByCNPJ(email);

        if (empresas == null)
            throw new EmpresaEmailNaoEncontradoException(email);

        return empresas;
    }

    public Empresas cadastrarEmpresa(EmpresasCreateCommand empresasCreateCommand) throws Exception {
        if(empresasRepository.findByEmail(empresasCreateCommand.getEmail_empresa()) != null)
            throw new EmpresaEmailCadastradoException(empresasCreateCommand.getEmail_empresa());

        if (empresasRepository.findByCNPJ(empresasCreateCommand.getCnpj_empresa()) != null)
            throw new EmpresaCNPJCadastradoException(empresasCreateCommand.getCnpj_empresa());

        Empresas empresasDomain = empresasCreateCommand.toEmpresa();
        empresasRepository.cadastrarEmpresa(empresasDomain);

        return findById(empresasDomain.getId_empresa());
    }

    public Empresas modificarEmpresa(EmpresasUpdateCommand empresasUpdateCommand, UUID id_empresa) throws Exception {
        Empresas empresasDomain = empresasRepository.findById(id_empresa);

        if (empresasDomain == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        empresasRepository.modificarEmpresa(empresasUpdateCommand.toEmpresa(id_empresa));
        return findById(id_empresa);
    }

    public void deletarEmpresaById(UUID id_empresa) throws Exception {
        Empresas empresasDomain = empresasRepository.findById(id_empresa);

        if (empresasDomain == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        empresasRepository.deleteEmpresaById(id_empresa);
    }
}
