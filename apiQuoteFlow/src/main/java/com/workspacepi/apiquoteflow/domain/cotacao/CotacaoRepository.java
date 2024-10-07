package com.workspacepi.apiquoteflow.domain.cotacao;

import java.util.List;
import java.util.UUID;

// Nossa interface cotacaoRepository que define a abstração dos métodos de consulta
public interface CotacaoRepository {

//  Buscar todas as cotações
    List<Cotacao> findAll();

//  Buscar por ID
    Cotacao findById(UUID id_cotacao);

//  Inserção
    Boolean solicitarCotacao(Cotacao cotacao);

//  Modificação
    Boolean modificarCotacao(Cotacao cotacao);

    Boolean deleteCotacaoById(UUID id_cotacao);
}
