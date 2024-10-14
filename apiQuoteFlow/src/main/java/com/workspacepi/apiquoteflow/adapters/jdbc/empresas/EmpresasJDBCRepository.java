package com.workspacepi.apiquoteflow.adapters.jdbc.empresas;

import com.workspacepi.apiquoteflow.adapters.http.empresas.error.EmpresasErrorHandler;
import com.workspacepi.apiquoteflow.domain.empresas.Empresas;
import com.workspacepi.apiquoteflow.domain.empresas.EmpresasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.empresas.EmpresasSqlExpressions.*;


@Repository
public class EmpresasJDBCRepository implements EmpresasRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public EmpresasJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresasErrorHandler.class);

    private RowMapper<Empresas> createEmpresaRowMapper() {
        return (rs, rowNum) -> {
            UUID id_empresa = UUID.fromString(rs.getString("id_empresa"));
            String cpnj_empresa = rs.getString("cnpj");
            String email_empresa = rs.getString("email");
            String nome_empresa = rs.getString("nome");
            String senha_empresa = rs.getString("senha");
            return new Empresas(id_empresa, cpnj_empresa, email_empresa, nome_empresa, senha_empresa);
        };
    }

    private MapSqlParameterSource parameterSource(Empresas empresas) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_empresa", empresas.getId_empresa());
        params.addValue("cnpj", empresas.getCnpj());
        params.addValue("email", empresas.getEmail());
        params.addValue("nome", empresas.getNome());
        params.addValue("senha", empresas.getSenha());
        return params;
    }

    @Override
    public List<Empresas> findAll() {
        List<Empresas> empresas = List.of();
        try {
            empresas = jdbcTemplate.query(sqlSelectAllEmpresas(), createEmpresaRowMapper());
            return empresas;
        } catch (Exception e) {
            LOGGER.error("Houver um erro ao consultar as empresas: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Empresas findById(UUID id_empresa) {
        List<Empresas> empresas;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_empresa", id_empresa);
            empresas = jdbcTemplate.query(sqlSelectEmpresaById(), params, createEmpresaRowMapper());
            return empresas.isEmpty() ? null : empresas.get(0);
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao consultar a empresa: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean cadastrarEmpresa(Empresas empresas) {
        try {
            MapSqlParameterSource params = parameterSource(empresas);
            int numLinhasAfetadas = jdbcTemplate.update(sqlNewEmpresa(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao cadastrar a empresa: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarEmpresa(Empresas empresas) {
        try {
            MapSqlParameterSource params = parameterSource(empresas);
            int numLinhasAfetadas = jdbcTemplate.update(sqlUpdateEmpresa(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houver um erro ao atualizar a empresa: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteEmpresaById(UUID id_empresa) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_empresa", id_empresa);
            int numLinhasAfetas = jdbcTemplate.update(sqlDeleteEmpresaById(), params);
            return numLinhasAfetas == 1;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao deletar a empresa: " + id_empresa + ". \n" + e.getMessage());
            throw e;
        }
    }
}
