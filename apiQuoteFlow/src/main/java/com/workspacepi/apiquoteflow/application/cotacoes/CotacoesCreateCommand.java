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

    @JsonProperty("numero")
    private int numero;

    @JsonProperty("status")
    private CotacaoStatus status;

    @JsonProperty("id_empresa")
    private UUID id_empresa;

    @JsonProperty("produtos")
    private List<ProdutosCotacao> produtos;

    @JsonProperty("destinatarios")
    private List<Destinatarios> destinatarios;

//  Conversão para cotacao
    public Cotacoes toCotacao() {
        return new Cotacoes(numero, status, id_empresa, produtos, destinatarios);
    }

}
