// Nosso repositório de acesso a dados


package com.workspacepi.apiquoteflow.adapters.jdbc.produtos;


import com.workspacepi.apiquoteflow.adapters.http.allErrors.ErrorHandler;
import com.workspacepi.apiquoteflow.domain.produtos.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 import org.springframework.dao.DataAccessException;
 import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
 import java.util.NoSuchElementException;
 import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.produtos.ProdutosSqlRepository.*;


// Nosso repositório que define os nossos métodos de query e de crud usando o JDBC

@Repository
public class ProdutosJDBCRepository implements ProdutosRepository {

//  Um atributo para criar o nosso template do JDBC assim como o seu construtor

private final NamedParameterJdbcTemplate jdbcTemplate;

public ProdutosJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}


//  Logger cuida do envio das nossas exceptions específicas ao invés das exceptions padrões

     private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);


//  Função da RowMapper para aproveitamento de código
//  Essa função é usada para mapear o resultado de uma consulta SQL

//    Implementação individual:

    private RowMapper<Produtos> produtosRowMapper() {
        return (rs, rowNum) -> {
            UUID id_produto = UUID.fromString(rs.getString("id_produto"));
            String sku = rs.getString("sku");
            String descricao = rs.getString("descricao");
            CategoriaProduto categoria = CategoriaProduto.valueOf(rs.getString("categoria"));
            String variacao = rs.getString("variacao");
            String observacao = rs.getString("observacao");
            UUID id_empresa = UUID.fromString(rs.getString("id_empresa"));

            return new Produtos(categoria, descricao, observacao, sku, variacao);
        };
    }

    private MapSqlParameterSource parameterSource(Produtos produto) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_produto", produto.getId_produto());
        params.addValue("sku", produto.getSku());
        params.addValue("descricao", produto.getDescricao());
        params.addValue("categoria", produto.getCategoria().name());
        params.addValue("variacao", produto.getVariacao());
        params.addValue("observacao", produto.getObservacao());
        params.addValue("id_empresa", produto.getId_empresa());
        return params;
    }

//    Implementação relacional:

    private RowMapper<Produtos> produtosRowMapper(UUID id_empresa) {
        return (rs, rowNum) -> {
            UUID id_produto = UUID.fromString(rs.getString("id_produto"));
            CategoriaProduto categoria = CategoriaProduto.valueOf(rs.getString("categoria"));
            String descricao = rs.getString("descricao");
            String observacao = rs.getString("observacao");
            String sku = rs.getString("sku");
            String variacao = rs.getString("variacao");
            return new Produtos(id_produto, categoria, descricao, observacao, sku, variacao, id_empresa);
        };
    }

    private MapSqlParameterSource parameterSource(Produtos produto, UUID id_empresa) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_produto", produto.getId_produto());
        params.addValue("categoria", produto.getCategoria().name());
        params.addValue("descricao", produto.getDescricao());
        params.addValue("observacao", produto.getObservacao());
        params.addValue("sku", produto.getSku());
        params.addValue("variacao", produto.getVariacao());
        params.addValue("id_empresa", id_empresa);
        return params;
    }

//    Implementações individuais:

    @Override
    public List<Produtos> findAll() {
        List<Produtos> produtos = List.of();
        try {
            produtos = jdbcTemplate.query(sqlSelectAllProdutos(), produtosRowMapper());
            return produtos;
        } catch (Exception e) {
            LOGGER.error("Houver um erro ao consultar os usuários: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Produtos findById(UUID id_produto) {
        List<Produtos> produtos = List.of();
        try {
            produtos = jdbcTemplate.query(sqlSelectProdutoById(), produtosRowMapper());
            return produtos.get(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean cadastrarProduto(Produtos produto) {
        try {
            MapSqlParameterSource params = parameterSource(produto);
            return jdbcTemplate.update(sqlCadastrarProduto(), params) > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao cadastrar o produto: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarProduto(Produtos produto) {
        try {
            MapSqlParameterSource params = parameterSource(produto);
            int numLinhasAfetadas = jdbcTemplate.update(sqlModificarProduto(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao modificar o produto: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteProdutoById(UUID id_produto) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_produto", id_produto);
            int numLinhasAfetadas = jdbcTemplate.update(sqlDeleteProdutoById(), params);
            return numLinhasAfetadas == 1;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao deletar o produto: " + e.getMessage());
            throw e;
        }
    }

    //    Novas implementações (definitivas):
    @Override
    public List<Produtos> findAllByEmpresa(UUID id_empresa) {
        List<Produtos> produtos;

        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_empresa", id_empresa);
            produtos = jdbcTemplate.query(sqlAllProdutosByEmpresa(), params, produtosRowMapper(id_empresa));
            return produtos;
        } catch (DataAccessException e) {
            LOGGER.error("Houve um erro ao consultar os produtos da empresa {}: {}", id_empresa, e.getMessage());
            throw e;
        }
    }

    @Override
    public Produtos findByIdAndEmpresa(UUID id_produto, UUID id_empresa) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_produto", id_produto);
        params.addValue("id_empresa", id_empresa);

        List<Produtos> produtos = jdbcTemplate.query(sqlProdutoByIdAndEmpresa(), params, produtosRowMapper(id_empresa));

        return produtos.isEmpty() ? null : produtos.get(0);
    }

    @Override
    public Boolean cadastrarProdutoInEmpresa(Produtos produto, UUID id_empresa) {
        try {
            MapSqlParameterSource params = parameterSource(produto);
            return jdbcTemplate.update(sqlCadastrarProdutoInEmpresa(), params) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("Houve um erro ao cadastrar o produto: {}.\n{}", produto.toString(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarProdutoInEmpresa(Produtos produto, UUID id_empresa) {
        try {
            MapSqlParameterSource params = parameterSource(produto, id_empresa);
            return jdbcTemplate.update(sqlModificarProdutoInEmpresa(), params) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("Houve um erro ao modificar o produto: {}\n{}", produto.toString(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteProdutoByIdAndEmpresa(UUID id_produto, UUID id_empresa) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_produto", id_produto);
            params.addValue("id_empresa", id_empresa);
            return jdbcTemplate.update(sqlDeleteProdutoInEmpresa(), params) == 1;
        } catch (DataAccessException e) {
            LOGGER.error("Houve um erro ao deletar o produto: {}. \n{}", id_produto, e.getMessage());
            throw e;
        }
    }

}
