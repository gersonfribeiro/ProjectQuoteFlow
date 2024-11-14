package com.workspacepi.apiquoteflow.application.cotacoes.destinatarios;

import com.workspacepi.apiquoteflow.domain.cotacoes.CotacoesRepository;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.DestinatariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DestinatariosService {

    private final DestinatariosRepository destinatariosRepository;
    private final CotacoesRepository cotacaoRepository;

    public DestinatariosService(DestinatariosRepository destinatariosRepository, CotacoesRepository cotacaoRepository) {
        this.destinatariosRepository = destinatariosRepository;
        this.cotacaoRepository = cotacaoRepository;
    }

    public List<Destinatarios> findAllDestinatariosByCotacao(UUID id_cotacao) {
        return destinatariosRepository.findAllDestinatariosByCotacao(id_cotacao);
    }

    public Destinatarios findEmpresaDestinatariaByCotacaoAndId(UUID id_cotacao, UUID id_destinatario) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotação não encontrada");

        return destinatariosRepository.findEmpresaDestinatariaByCotacaoAndId(id_cotacao, id_destinatario);
    }

    public Destinatarios inserirDestinatario(DestinatariosCreateCommand destinatario, UUID id_cotacao) {
        if(cotacaoRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotação não encontrada");

        Destinatarios destinatarioDomain = destinatario.toDestinatario();
        destinatariosRepository.inserirDestinatario(destinatarioDomain, id_cotacao);
        return findEmpresaDestinatariaByCotacaoAndId(id_cotacao, destinatarioDomain.getId_destinatario());
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
