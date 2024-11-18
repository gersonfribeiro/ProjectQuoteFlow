package com.workspacepi.apiquoteflow.application.cotacoes;

import com.workspacepi.apiquoteflow.application.cotacoes.exceptions.CotacaoNaoEncontradaException;
import com.workspacepi.apiquoteflow.domain.cotacoes.Cotacoes;
import com.workspacepi.apiquoteflow.domain.cotacoes.CotacoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Nossa classe de serviços das cotações, aqui definimos as regras de negócios do nosso projeto no que diz respeito as cotações

@Service
public class CotacoesService {

//  Atributo do nosso repository onde temos a nossa interface que
//  declara a abstração que devemos possuir nos métodos crud usando o JDBC
//  assim como o seu construtor

    private final CotacoesRepository cotacoesRepository;
    public CotacoesService(CotacoesRepository cotacoesRepository) {
        this.cotacoesRepository = cotacoesRepository;
    }

//  Retorno do método findAll

    public List<Cotacoes> findAll() {
        return cotacoesRepository.findAll();
    }

    public List<Cotacoes> findAllByEmpresa(UUID id_empresa) {
        return cotacoesRepository.findAllByEmpresa(id_empresa);
    }

//  Retorno do método findById com a possíbilidade de retornar uma exception criada manualmente

    public Cotacoes findById(UUID id_cotacao) throws Exception{
        Cotacoes cotacoes = cotacoesRepository.findById(id_cotacao);

        if(cotacoes == null)
            throw new CotacaoNaoEncontradaException(id_cotacao);

        return cotacoes;
    }

    public Cotacoes solicitarCotacao(CotacoesCreateCommand cotacoesCreateCommand) throws Exception {
        Cotacoes cotacoesDomain = cotacoesCreateCommand.toCotacao();

        List<Cotacoes> numCotacoesEmpresa = cotacoesRepository.findAllByEmpresa(cotacoesDomain.getId_empresa());
        cotacoesDomain.setNumero(numCotacoesEmpresa.size() + 1);
        System.out.println(numCotacoesEmpresa.size());

        cotacoesRepository.solicitarCotacao(cotacoesDomain);

        return findById(cotacoesDomain.getId_cotacao());
    }

    public Cotacoes modificarCotacao(CotacoesUpdateCommand cotacaoUpdateCommand, UUID id_cotacao) throws Exception {
        Cotacoes cotacaoDomain = cotacoesRepository.findById(id_cotacao);


        if (cotacaoDomain == null) {
            throw new CotacaoNaoEncontradaException(id_cotacao);
        }

        cotacaoDomain.setStatus(cotacaoUpdateCommand.getStatus());

        cotacoesRepository.modificarCotacao(cotacaoDomain);
        return findById(id_cotacao);

    }

    public void deleteCotacaoById(UUID id_cotacao) throws Exception {
        Cotacoes cotacoesDomain = cotacoesRepository.findById(id_cotacao);

        if (cotacoesDomain == null) {
            throw new CotacaoNaoEncontradaException(id_cotacao);
        }

        cotacoesRepository.deleteCotacaoById(id_cotacao);
    }

}
