package com.workspacepi.apiquoteflow.application.cotacoes.destinatarios;

import com.workspacepi.apiquoteflow.domain.cotacoes.CotacoesRepository;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.DestinatariosRepository;
import com.workspacepi.apiquoteflow.domain.empresas.EmpresasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DestinatariosService {

    private final DestinatariosRepository destinatariosRepository;
    private final CotacoesRepository cotacaoRepository;
    private final EmpresasRepository empresasRepository;

    public DestinatariosService(DestinatariosRepository destinatariosRepository,
                                CotacoesRepository cotacaoRepository,
                                EmpresasRepository empresasRepository) {
        this.destinatariosRepository = destinatariosRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.empresasRepository = empresasRepository;
    }

    public List<Destinatarios> findAllDestinatariosByCotacao(UUID id_cotacao) {
        return destinatariosRepository.findAllDestinatariosByCotacao(id_cotacao);
    }

    public Destinatarios findEmpresaDestinatariaByCotacaoAndId(UUID id_cotacao, UUID id_destinatario) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotação não encontrada");

        return destinatariosRepository.findEmpresaDestinatariaByCotacaoAndId(id_cotacao, id_destinatario);
    }

    public List<Destinatarios> inserirDestinatario(List<DestinatariosCreateCommand> destinatarios, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotação não encontrada");

        List<Destinatarios> destinatariosDomain = destinatarios.stream()
            .map(command -> {
                if(empresasRepository.findById(command.getId_destinatario()) == null)
                    throw new RuntimeException("Empresa não encontrada!");
                command.setId_cotacao(id_cotacao);

                return command.toDestinatario();
            })
                .toList();

        destinatariosRepository.inserirDestinatario(destinatariosDomain, id_cotacao);
        return destinatariosDomain;
    }

    public Destinatarios modificarDestinatario(DestinatariosUpdateCommand destinatario, UUID id_destinatario, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotação não encontrada");

        Destinatarios destinatarioDomain = destinatario.toDestinatario(id_destinatario);
        destinatariosRepository.modificarDestinatario(destinatarioDomain, id_destinatario, id_cotacao);
        return findEmpresaDestinatariaByCotacaoAndId(id_cotacao, destinatarioDomain.getId_destinatario());
    }

    public void removerDestinatario(UUID id_destinatario, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotação não encontrada");

        destinatariosRepository.removerDestinatario(id_destinatario, id_cotacao);
    }
}
