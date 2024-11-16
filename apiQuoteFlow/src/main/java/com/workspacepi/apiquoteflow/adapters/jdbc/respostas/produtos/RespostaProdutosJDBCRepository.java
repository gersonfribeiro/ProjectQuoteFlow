package com.workspacepi.apiquoteflow.adapters.jdbc.respostas.produtos;

import com.workspacepi.apiquoteflow.adapters.http.allErrors.ErrorHandler;
import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutos;
import com.workspacepi.apiquoteflow.domain.respostas.produtos.RespostaProdutosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.respostas.produtos.RespostaProdutosSqlExpressions.*;

@Repository
public class RespostaProdutosJDBCRepository implements RespostaProdutosRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RespostaProdutosJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    private RowMapper<RespostaProdutos> respostaProdutosRowMapper() {
        return (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            UUID id_resposta = UUID.fromString(rs.getString("id_resposta"));
            UUID id_produto = UUID.fromString(rs.getString("id_produto"));
            float preco = Float.parseFloat(rs.getString("preco"));
            String observacao = rs.getString("observacao");

            return new RespostaProdutos(id, id_resposta, id_produto, preco, observacao);
        };
    }

    private MapSqlParameterSource parameterSource(RespostaProdutos respostaProdutos) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", respostaProdutos.getId());
        params.addValue("id_resposta", respostaProdutos.getId_resposta());
        params.addValue("id_produto", respostaProdutos.getId_produto());
        params.addValue("preco", respostaProdutos.getPreco());
        params.addValue("observacao", respostaProdutos.getObservacao());

        return params;
    }

    @Override
    public List<RespostaProdutos> respostaProdutos(UUID id_resposta) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id_resposta", id_resposta);
            return jdbcTemplate.query(sqlFindRespostasProdutos(), params, respostaProdutosRowMapper());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public RespostaProdutos respostaProduto(UUID id_resposta, UUID id_produto) {
        List<RespostaProdutos> respostaProdutos;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id_resposta", id_resposta);
            params.addValue("id_produto", id_produto);
            respostaProdutos = jdbcTemplate.query(sqlFindRespostaProdutos(), params, respostaProdutosRowMapper());
            return respostaProdutos.isEmpty() ? null : respostaProdutos.get(0);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean registrarRespostaProduto(RespostaProdutos respostaProdutos, UUID id_resposta, UUID id_produto) {
        try {
            MapSqlParameterSource params = parameterSource(respostaProdutos);
            params.addValue("id_resposta", id_resposta);
            params.addValue("id_produto", id_produto);

            return jdbcTemplate.update(sqlEnviarResposta(), params) > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarRespostaProduto(RespostaProdutos respostaProdutos, UUID id_resposta, UUID id_produto) {
        try {
            MapSqlParameterSource params = parameterSource(respostaProdutos);
            params.addValue("id_resposta", id_resposta);
            params.addValue("id_produto", id_produto);

            return jdbcTemplate.update(sqlModificarResposta(), params) > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deletarRespostaProduto(UUID id_resposta, UUID id_produto) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id_resposta", id_resposta);
            params.addValue("id_produto", id_produto);

            return jdbcTemplate.update(sqlRemoverResposta(), params) > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }
}
