package com.workspacepi.apiquoteflow.application.respostas;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CotacaoComProdutosDTO {
    private UUID id;
    private Timestamp dataEnvio;
    private UUID idCotacao;
    private String status;
    private List<ProdutoDTO> produtos;

}
