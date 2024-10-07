// Nosso repositório de acesso a dados


package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes;


import com.workspacepi.apiquoteflow.adapters.http.enderecos.error.EnderecosErrorHandler;
import com.workspacepi.apiquoteflow.domain.cotacao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes.CotacoesSqlExpressions.*;


// Nosso repositório que define os nossos métodos de query e de crud usando o JDBC

@Repository
public class CotacoesJDBCRepository implements CotacaoRepository {

//  Um atributo para criar o nosso template do JDBC assim como o seu construtor

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CotacoesJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//  Logger cuida do envio das nossas exceptions específicas ao invés das exceptions padrões

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecosErrorHandler.class);


//  Função da RowMapper para aproveitamento de código
//  Essa função é usada para mapear o resultado de uma consulta SQL

    private RowMapper<Cotacao> createCotacaoRowMapper() {
        return (rs, rowNum) -> {
            UUID id_cotacao = UUID.fromString(rs.getString("id_cotacao"));
            Timestamp data_cotacao = rs.getTimestamp("data_cotacao");
            int numero_cotacao = rs.getRow();
            CotacaoStatus status_cotacao = CotacaoStatus.valueOf(rs.getString("status_cotacao"));
            UUID id_empresa_cotacao = UUID.fromString(rs.getString("id_empresa_cotacao"));

            return new Cotacao(id_cotacao, data_cotacao, numero_cotacao, status_cotacao, id_empresa_cotacao,null);
        };
    }

//  Função para mapeamento dos parâmetros para as consultas sql

    private MapSqlParameterSource parameterSource(Cotacao cotacao) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_cotacao", cotacao.getId_cotacao());
        params.addValue("data_cotacao", cotacao.getData_cotacao());
        params.addValue("numero_cotacao", cotacao.getNumero_cotacao());
        params.addValue("status_cotacao", cotacao.getStatus_cotacao().name());
        params.addValue("id_empresa_cotacao", cotacao.getId_empresa_cotacao());
        params.addValue("itens", cotacao.getItens());
        return params;
    }

//  Reescrita do método findAll definito na nossa interface de cotações

    @Override
    public List<Cotacao> findAll() {
        List<Cotacao> cotacoes = List.of();
        try {
            cotacoes = jdbcTemplate.query(sqlSelectAllQuotations(), createCotacaoRowMapper());
            return cotacoes;

        } catch (Exception e) {
            LOGGER.error("Houve um erro ao consultar as cotações: " + e.getMessage());
            throw e;
        }
    }

//  Reescrita do método findById definido na nossa interface de cotações

    @Override
    public Cotacao findById(UUID id_cotacao) {
        List<Cotacao> cotacoes;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_cotacao", id_cotacao);
            cotacoes = jdbcTemplate.query(sqlSelectQuotationById(), params, createCotacaoRowMapper());
            return cotacoes.isEmpty() ? null : cotacoes.get(0);
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao consultar a cotação: " + e.getMessage());
            throw e;
        }
    }

//  Método de inserção de cotações

    @Override
    public Boolean solicitarCotacao(Cotacao cotacao) {
        try {
            MapSqlParameterSource params = parameterSource(cotacao);
            int numLinhasAfetadas = jdbcTemplate.update(sqlSolicitarCotacao(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao solicitar a cotacao: " + e.getMessage());
            throw e;
        }
    }

//  Método de atualização de cotações

    @Override
    public Boolean modificarCotacao(Cotacao cotacao) {
        try {
            MapSqlParameterSource params = parameterSource(cotacao);
            int numLinhasAfetadas = jdbcTemplate.update(sqlModificarCotacao(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao atualizar a cotacao: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Boolean deleteCotacaoById(UUID id_cotacao) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_cotacao", id_cotacao);
            int numLinhasAfetadas = jdbcTemplate.update(sqlDeleteCotacaoById(), params);
            return numLinhasAfetadas == 1;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao deletar a cotacao: " + id_cotacao + ". \n" + e.getMessage());
            throw e;
        }
    }
}
