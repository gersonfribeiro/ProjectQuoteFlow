package com.workspacepi.apiquoteflow.adapters.jdbc.respostas;

import com.workspacepi.apiquoteflow.adapters.http.allErrors.ErrorHandler;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.respostas.RespostaCotacaoSqlExpressions.*;


@Repository
public class RespostaCotacaoJDBCRepository implements RespostaCotacaoRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RespostaCotacaoJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    private RowMapper<RespostaCotacao> respostaCotacaoRowMapper() {
        return (rs, rowNum) -> {
            UUID id_resposta = UUID.fromString(rs.getString("id_resposta"));
            Timestamp data_resposta = Timestamp.valueOf(rs.getString("data_resposta"));
            UUID id_empresa_autora = UUID.fromString(rs.getString("id_empresa_autora"));
            UUID id_cotacao = UUID.fromString(rs.getString("id_cotacao"));

            return new RespostaCotacao(id_resposta, data_resposta, id_empresa_autora, id_cotacao);
        };
    }

    private MapSqlParameterSource parameterSource(RespostaCotacao respostaCotacao) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id_resposta", respostaCotacao.getId_resposta());
        params.addValue("data_resposta", respostaCotacao.getData_resposta());
        params.addValue("id_empresa_resposta", respostaCotacao.getId_empresa_resposta());
        params.addValue("id_cotacao", respostaCotacao.getId_cotacao());

        return params;
    }

    @Override
    public List<RespostaCotacao> respostasCotacao(UUID id_empresa_resposta) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id_empresa_resposta", id_empresa_resposta);

            return jdbcTemplate.query(sqlFindRespostasCotacaoByEmpresa(), params, respostaCotacaoRowMapper());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public RespostaCotacao respostaCotacao(UUID id_empresa_resposta, UUID id_cotacao) {
        List<RespostaCotacao> resposta;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id_empresa_resposta", id_empresa_resposta);
            params.addValue("id_cotacao", id_cotacao);

            resposta = jdbcTemplate.query(sqlFindRespostaCotacaoByEmpresaAndCotacao(), params, respostaCotacaoRowMapper());
            return resposta.isEmpty() ? null : resposta.get(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean registrarResposta(RespostaCotacao resposta, UUID id_empresa_resposta, UUID id_cotacao) {
        try {
            MapSqlParameterSource params = parameterSource(resposta);
            params.addValue("id_empresa_resposta", id_empresa_resposta);
            params.addValue("id_cotacao", id_cotacao);

            return jdbcTemplate.update(sqlEnviarRespostaCotacao(), params) > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarResposta(RespostaCotacao resposta, UUID id_empresa_resposta, UUID id_cotacao) {
        try {
            MapSqlParameterSource params = parameterSource(resposta);
            params.addValue("id_empresa_resposta", id_empresa_resposta);
            params.addValue("id_cotacao", id_cotacao);

            return jdbcTemplate.update(sqlModificarResposta(), params) > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deletarResposta(UUID id_empresa_resposta, UUID id_cotacao) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id_empresa_resposta", id_empresa_resposta);
            params.addValue("id_cotaoca", id_cotacao);

            return jdbcTemplate.update(sqlRemoverResposta(), params) > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }
}
