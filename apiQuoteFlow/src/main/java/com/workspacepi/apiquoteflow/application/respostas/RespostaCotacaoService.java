package com.workspacepi.apiquoteflow.application.respostas;

import com.workspacepi.apiquoteflow.application.empresas.exceptions.EmpresaIdNaoEncontradaException;
import com.workspacepi.apiquoteflow.domain.cotacoes.CotacoesRepository;
import com.workspacepi.apiquoteflow.domain.empresas.EmpresasRepository;
import com.workspacepi.apiquoteflow.domain.produtos.ProdutosRepository;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RespostaCotacaoService {
    private final EmpresasRepository empresasRepository;
    private final CotacoesRepository cotacoesRepository;
    private final ProdutosRepository produtosRepository;
    private final RespostaCotacaoRepository respostaCotacaoRepository;

    public RespostaCotacaoService(EmpresasRepository empresasRepository,
                                  CotacoesRepository cotacoesRepository,
                                  ProdutosRepository produtosRepository,
                                  RespostaCotacaoRepository respostaCotacaoRepository) {
        this.empresasRepository = empresasRepository;
        this.cotacoesRepository = cotacoesRepository;
        this.produtosRepository = produtosRepository;
        this.respostaCotacaoRepository = respostaCotacaoRepository;
    }

    public List<RespostaCotacao> respostasCotacao(UUID id_empresa_resposta) {
        if(empresasRepository.findById(id_empresa_resposta) == null)
            throw new RuntimeException("Empresa não encontrada!");

        return respostaCotacaoRepository.respostasCotacao(id_empresa_resposta);
    }

    public List<CotacaoComProdutosDTO> buscarCotacoesComProdutos(UUID id_empresa_resposta) {
        if(empresasRepository.findById(id_empresa_resposta) == null)
            throw new RuntimeException("Empresa não encontrada!");

        return respostaCotacaoRepository.buscarCotacoesComProdutos(id_empresa_resposta);
    }

    public RespostaCotacao respostaCotacao(UUID id_empresa_resposta, UUID id_cotacao) {
        if(empresasRepository.findById(id_empresa_resposta) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa_resposta);

        if(cotacoesRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacao não encontrada!");

        return respostaCotacaoRepository.respostaCotacao(id_empresa_resposta, id_cotacao);
    }

    public RespostaCotacao registrarResposta(RespostaCotacaoCreateCommand resposta,
                                             UUID id_empresa_resposta,
                                             UUID id_cotacao) {
        if(empresasRepository.findById(id_empresa_resposta) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa_resposta);

        if(produtosRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacao não encontrada!");

        RespostaCotacao respostaDomain = resposta.toRespostaCotacao();
        respostaCotacaoRepository.registrarResposta(respostaDomain, id_empresa_resposta, id_cotacao);

        return respostaCotacaoRepository.respostaCotacao(id_empresa_resposta, id_cotacao);
    }

    public RespostaCotacao modificarResposta(RespostaCotacaoUpdateCommand resposta,
                                             UUID id_empresa_resposta,
                                             UUID id_cotacao) {
        if(empresasRepository.findById(id_empresa_resposta) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa_resposta);

        if(produtosRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacao não encontrada!");

        RespostaCotacao id_resposta = respostaCotacaoRepository.respostaCotacao(id_empresa_resposta, id_cotacao);

        RespostaCotacao respostaDomain = resposta.toRespostaCotacao(id_resposta.getId_resposta());
        respostaCotacaoRepository.registrarResposta(respostaDomain, id_empresa_resposta, id_cotacao);

        return respostaCotacaoRepository.respostaCotacao(id_empresa_resposta, id_cotacao);
    }

    public void deletarResposta(UUID id_empresa_resposta, UUID id_cotacao) {

        if(empresasRepository.findById(id_empresa_resposta) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa_resposta);

        if(produtosRepository.findById(id_cotacao) == null)
            throw new RuntimeException("Cotacao não encontrada!");

        respostaCotacaoRepository.deletarResposta(id_empresa_resposta, id_cotacao);
    }
}
