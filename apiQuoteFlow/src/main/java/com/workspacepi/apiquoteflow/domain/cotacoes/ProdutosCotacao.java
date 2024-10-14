package com.workspacepi.apiquoteflow.domain.cotacoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// Uma segunda tabela criada para definir um relacionamento entre as cotações e seus itens

@Getter
@Setter
@AllArgsConstructor
public class ProdutosCotacao {
    private UUID id_produto;
    private int quantidade;
    private UUID id_cotacao;

}
