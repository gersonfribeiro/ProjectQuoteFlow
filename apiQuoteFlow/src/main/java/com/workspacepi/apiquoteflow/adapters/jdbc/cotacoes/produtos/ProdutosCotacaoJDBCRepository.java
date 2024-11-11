package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes.produtos;

import com.workspacepi.apiquoteflow.adapters.http.allErrors.ErrorHandler;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacao;
import com.workspacepi.apiquoteflow.domain.cotacoes.produtos.ProdutosCotacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes.produtos.ProdutosCotacaoSqlExpressions.*;

@Repository
public class ProdutosCotacaoJDBCRepository implements ProdutosCotacaoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProdutosCotacaoJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    private RowMapper<ProdutosCotacao> createProdutosCotacaoRowMapper() {
        return(rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            UUID id_cotacao = UUID.fromString(rs.getString("id_cotacao"));
            UUID id_produto = UUID.fromString(rs.getString("id_produto"));
            int quantidade = rs.getInt("quantidade");

            return new ProdutosCotacao(id, id_cotacao, id_produto, quantidade);
        };
    }

    private MapSqlParameterSource parameterSource(ProdutosCotacao produtosCotacao, UUID id_produto, UUID id_cotacao) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", produtosCotacao.getId());
        params.addValue("id_cotacao", id_cotacao);
        params.addValue("id_produto", id_produto);
        params.addValue("quantidade", produtosCotacao.getQuantidade());
        return params;
    }

    private MapSqlParameterSource parameterSource(ProdutosCotacao produtosCotacao, UUID id_cotacao) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", produtosCotacao.getId());
        params.addValue("id_cotacao", id_cotacao);
        params.addValue("id_produto", produtosCotacao.getId_produto());
        params.addValue("quantidade", produtosCotacao.getQuantidade());
        return params;
    }

    @Override
    public List<ProdutosCotacao> findAllProdutosByCotacao(UUID id_cotacao) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_cotacao", id_cotacao);
            return jdbcTemplate.query(sqlFindAllProdutosByCotacao(), params, createProdutosCotacaoRowMapper());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ProdutosCotacao findProdutoByCotacaoAndId(UUID id_cotacao, UUID id_produto) {
        List<ProdutosCotacao> produtosCotacao;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_cotacao", id_cotacao);
            params.addValue("id_produto", id_produto);
            produtosCotacao = jdbcTemplate.query(sqlFindProdutoByCotacaoAndId(), params, createProdutosCotacaoRowMapper());
            return produtosCotacao.isEmpty() ? null : produtosCotacao.get(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean inserirProdutosCotacao(ProdutosCotacao produtos, UUID id_cotacao) {
        try {
            MapSqlParameterSource params = parameterSource(produtos, id_cotacao);
            int numLinhasAfetadas = jdbcTemplate.update(sqlInserirProdutosCotacao(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarProdutosCotacao(ProdutosCotacao produtos, UUID id_produto, UUID id_cotacao) {
        try {
            MapSqlParameterSource params = parameterSource(produtos, id_produto, id_cotacao);
            int numLinhasAfetadas = jdbcTemplate.update(sqlModificarProdutosCotacao(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteProdutosByCotacaoAndId(UUID id_cotacao, UUID id_produto) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_cotacao", id_cotacao);
            params.addValue("id_produto", id_produto);
            int numLinhasAfetadas = jdbcTemplate.update(sqlDeleteProdutosByCotacaoAndId(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

}
