package com.workspacepi.apiquoteflow.application.cotacoes;

import com.workspacepi.apiquoteflow.domain.cotacoes.Cotacoes;
import com.workspacepi.apiquoteflow.domain.cotacoes.CotacaoStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

//  Usando Jackson para serialização
@Setter
@Getter
public class CotacoesCreateCommand {

    @JsonProperty("id_empresa")
    private UUID id_empresa;

//  Conversão para cotacao
    public Cotacoes toCotacao() {
        return new Cotacoes(id_empresa);
    }

}
