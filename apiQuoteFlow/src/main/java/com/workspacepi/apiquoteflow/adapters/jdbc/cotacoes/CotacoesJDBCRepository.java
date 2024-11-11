// Nosso repositório de acesso a dados


package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes;


import com.workspacepi.apiquoteflow.adapters.http.allErrors.ErrorHandler;
import com.workspacepi.apiquoteflow.domain.cotacoes.*;
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
public class CotacoesJDBCRepository implements CotacoesRepository {

//  Um atributo para criar o nosso template do JDBC assim como o seu construtor

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CotacoesJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//  Logger cuida do envio das nossas exceptions específicas ao invés das exceptions padrões

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);


//  Função da RowMapper para aproveitamento de código
//  Essa função é usada para mapear o resultado de uma consulta SQL

    private RowMapper<Cotacoes> createCotacaoRowMapper() {
        return (rs, rowNum) -> {
            UUID id_cotacao = UUID.fromString(rs.getString("id_cotacao"));
            Timestamp data_cotacao = rs.getTimestamp("data");
            int numero_cotacao = rs.getInt("numero");
            CotacaoStatus status_cotacao = CotacaoStatus.valueOf(rs.getString("status"));
            UUID id_empresa_cotacao = UUID.fromString(rs.getString("id_empresa"));

            return new Cotacoes(id_cotacao, data_cotacao, numero_cotacao, status_cotacao, id_empresa_cotacao,null, null);
        };
    }

//  Função para mapeamento dos parâmetros para as consultas sql

    private MapSqlParameterSource parameterSource(Cotacoes cotacoes) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_cotacao", cotacoes.getId_cotacao());
        params.addValue("data", cotacoes.getData());
        params.addValue("numero", cotacoes.getNumero());
        params.addValue("status", cotacoes.getStatus().name());
        params.addValue("id_empresa", cotacoes.getId_empresa());
        return params;
    }

//  Reescrita do método findAll definito na nossa interface de cotações

    @Override
    public List<Cotacoes> findAll() {
        List<Cotacoes> cotacoes = List.of();
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
    public Cotacoes findById(UUID id_cotacao) {
        List<Cotacoes> cotacoes;
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
    public Boolean solicitarCotacao(Cotacoes cotacoes) {
        try {
            MapSqlParameterSource params = parameterSource(cotacoes);
            int numLinhasAfetadas = jdbcTemplate.update(sqlSolicitarCotacao(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao solicitar a cotacao: " + e.getMessage());
            throw e;
        }
    }

//  Método de atualização de cotações

    @Override
    public Boolean modificarCotacao(Cotacoes cotacoes) {
        try {
            MapSqlParameterSource params = parameterSource(cotacoes);
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
