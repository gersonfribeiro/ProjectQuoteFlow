package com.workspacepi.apiquoteflow.domain.cotacao;

import java.util.UUID;

// Uma segunda tabela criada para definir um relacionamento entre as cotações e seus itens

public class ItensCotacao {
    private UUID id_item = UUID.randomUUID();
    private String descricao;
    private int quantidade;
    private String observacoes;
}
