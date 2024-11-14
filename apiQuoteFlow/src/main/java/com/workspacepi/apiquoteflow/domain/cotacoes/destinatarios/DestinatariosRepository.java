package com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DestinatariosRepository {

    List<Destinatarios> findAllDestinatariosByCotacao(UUID id_cotacao);

    Destinatarios findEmpresaDestinatariaByCotacaoAndId(UUID id_cotacao, UUID id_destinatario);

    Boolean inserirDestinatario(Destinatarios destinatario, UUID id_cotacao);

    Boolean modificarDestinatario(Destinatarios destinatario, UUID id_cotacao, UUID id_destinatario);

    Boolean removerDestinatario(UUID id_cotacao, UUID id_destinatario);

}
