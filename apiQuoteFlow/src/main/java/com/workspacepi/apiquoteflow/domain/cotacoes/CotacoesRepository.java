package com.workspacepi.apiquoteflow.domain.cotacoes;

import java.util.List;
import java.util.UUID;

// Nossa interface cotacaoRepository que define a abstração dos métodos de consulta
public interface CotacoesRepository {

//  Buscar todas as cotações
    List<Cotacoes> findAll();

//  Buscar todas as cotações
    List<Cotacoes> findAllByEmpresa(UUID id_empresa);

//  Buscar por ID
    Cotacoes findById(UUID id_cotacao);

//  Inserção
    Boolean solicitarCotacao(Cotacoes cotacoes);

//  Modificação
    Boolean modificarCotacao(Cotacoes cotacoes);

//  Delete
    Boolean deleteCotacaoById(UUID id_cotacao);
}
