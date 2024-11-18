package com.workspacepi.apiquoteflow.adapters.jdbc.respostas;

import com.workspacepi.apiquoteflow.adapters.http.allErrors.ErrorHandler;
import com.workspacepi.apiquoteflow.application.respostas.CotacaoComProdutosDTO;
import com.workspacepi.apiquoteflow.application.respostas.ProdutoDTO;
import com.workspacepi.apiquoteflow.domain.cotacoes.destinatarios.Destinatarios;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacao;
import com.workspacepi.apiquoteflow.domain.respostas.RespostaCotacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

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
            UUID id_empresa_resposta = UUID.fromString(rs.getString("id_empresa_resposta"));
            UUID id_cotacao = UUID.fromString(rs.getString("id_cotacao"));

            return new RespostaCotacao(id_resposta, data_resposta, id_empresa_resposta, id_cotacao);
        };
    }

//    private RowMapper<Destinatarios> createDestinatariosRowMapper() {
//        return(rs, rowNum) -> {
//            UUID id =  UUID.fromString(rs.getString("id"));
//            Timestamp data_envio = rs.getTimestamp("data_envio");
//            UUID id_cotacao = UUID.fromString(rs.getString("id_cotacao"));
//            UUID id_destinatario = UUID.fromString(rs.getString("id_destinatario"));
//
//            return new Destinatarios(id, data_envio, id_cotacao, id_destinatario);
//        };
//    }

    private RowMapper<CotacaoComProdutosDTO> createCotacaoComProdutosRowMapper() {
        return (rs, rowNum) -> {
            CotacaoComProdutosDTO cotacao = new CotacaoComProdutosDTO();
            cotacao.setId(rs.getObject("id_destinatario", UUID.class));
            cotacao.setDataEnvio(rs.getTimestamp("data_envio"));
            cotacao.setIdCotacao(rs.getObject("id_cotacao", UUID.class));
            cotacao.setStatus(rs.getString("status"));

            ProdutoDTO produto = new ProdutoDTO();
            produto.setIdProduto(rs.getObject("id_produto", UUID.class));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQuantidade(rs.getInt("quantidade"));

            cotacao.setProdutos(new ArrayList<>()); // Inicializar lista de produtos
            cotacao.getProdutos().add(produto);

            return cotacao;
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

//    @Override
//    public List<Destinatarios> buscarRespostasPendentes(UUID id_empresa_resposta) {
//        try {
//            MapSqlParameterSource params = new MapSqlParameterSource();
//            params.addValue("id_destinatario", id_empresa_resposta);
//
//            return jdbcTemplate.query(sqlBuscarRespostasPendentes(), params, createDestinatariosRowMapper());
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            throw e;
//        }
//    }

    @Override
    public List<CotacaoComProdutosDTO> buscarCotacoesComProdutos(UUID idDestinatario) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_destinatario", idDestinatario);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlBuscarCotacoesComProdutos(), params);

        Map<UUID, CotacaoComProdutosDTO> cotacaoMap = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            UUID cotacaoId = (UUID) row.get("id_cotacao");

            // Se a cotação já existe no mapa, apenas adiciona o produto
            CotacaoComProdutosDTO cotacao = cotacaoMap.computeIfAbsent(cotacaoId, id -> {
                CotacaoComProdutosDTO novaCotacao = new CotacaoComProdutosDTO();
                novaCotacao.setId((UUID) row.get("id_destinatario"));
                novaCotacao.setDataEnvio((Timestamp) row.get("data_envio"));
                novaCotacao.setIdCotacao(cotacaoId);
                novaCotacao.setStatus((String) row.get("status"));
                novaCotacao.setProdutos(new ArrayList<>());
                return novaCotacao;
            });

            // Adiciona o produto
            ProdutoDTO produto = new ProdutoDTO();
            produto.setIdProduto((UUID) row.get("id_produto"));
            produto.setDescricao((String) row.get("descricao"));
            produto.setQuantidade((Integer) row.get("quantidade"));
            cotacao.getProdutos().add(produto);
        }

        return new ArrayList<>(cotacaoMap.values());
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
