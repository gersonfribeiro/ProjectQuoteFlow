package com.workspacepi.apiquoteflow.application.empresas;

import com.workspacepi.apiquoteflow.application.empresas.exceptions.EmpresaNaoEncontradaException;
import com.workspacepi.apiquoteflow.domain.cotacao.Cotacao;
import com.workspacepi.apiquoteflow.domain.empresas.Empresa;
import com.workspacepi.apiquoteflow.domain.empresas.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresasService {

    private final EmpresaRepository empresaRepository;

    public EmpresasService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> findAll() { return empresaRepository.findAll(); }

    public Empresa findById(UUID id_empresa) throws Exception {
        Empresa empresa = empresaRepository.findById(id_empresa);

        if (empresa == null)
            throw new Exception("Empresa não encontrada");

        return empresa;
    }

    public Empresa cadastrarEmpresa(EmpresasCreateCommand empresasCreateCommand) throws Exception {
        Empresa empresaDomain = empresasCreateCommand.toEmpresa();
        empresaRepository.cadastrarEmpresa(empresaDomain);

        return findById(empresaDomain.getId_empresa());
    }

    public Empresa modificarEmpresa(EmpresasUpdateCommand empresasUpdateCommand, UUID id_empresa) throws Exception {
        Empresa empresaDomain = empresaRepository.findById(id_empresa);

        if (empresaDomain == null)
            throw new EmpresaNaoEncontradaException(id_empresa);

        empresaRepository.modificarEmpresa(empresasUpdateCommand.toEmpresa(id_empresa));
        return findById(id_empresa);
    }

    public void deletarEmpresaById(UUID id_empresa) throws Exception {
        Empresa empresaDomain = empresaRepository.findById(id_empresa);

        if (empresaDomain == null)
            throw new EmpresaNaoEncontradaException(id_empresa);

        empresaRepository.deleteEmpresaById(id_empresa);
    }
}
