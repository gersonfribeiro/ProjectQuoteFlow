package com.workspacepi.apiquoteflow.adapters.jdbc.usuarios;

import com.workspacepi.apiquoteflow.adapters.http.enderecos.error.EnderecosErrorHandler;
import com.workspacepi.apiquoteflow.adapters.http.usuarios.error.UsuarioErrorHandler;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import com.workspacepi.apiquoteflow.domain.usuarios.UsuariosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.workspacepi.apiquoteflow.adapters.jdbc.usuarios.UsuariosSqlExpressions.*;


@Repository
public class UsuariosJDBCRepository implements UsuariosRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public UsuariosJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioErrorHandler.class);

    private RowMapper<Usuarios> createUsuariosRowMapper() {
        return (rs, rowNum) -> {
            UUID id_usuario = UUID.fromString(rs.getString("id_usuario"));
            String nome_usuario = rs.getString("nome_usuario");
            String email_usuario = rs.getString("email_usuario");
            String senha_usuario = rs.getString("senha_usuario");
            String telefone_usuario = rs.getString("telefone_usuario");
            UUID id_empresa_usuario = UUID.fromString(rs.getString("id_empresa_usuario"));
            return new Usuarios(id_usuario, nome_usuario, email_usuario, senha_usuario, telefone_usuario, id_empresa_usuario);
        };
    }

    private MapSqlParameterSource parameterSource(Usuarios usuario) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_usuario", usuario.getId_usuario());
        params.addValue("nome_usuario", usuario.getNome_usuario());
        params.addValue("email_usuario", usuario.getEmail_usuario());
        params.addValue("senha_usuario", usuario.getSenha_usuario());

        // Campos opcionais
        if (usuario.getTelefone_usuario() != null) {
            params.addValue("telefone_usuario", usuario.getTelefone_usuario());
        } else {
            params.addValue("telefone_usuario", null);
        }

        if (usuario.getId_empresa_usuario() != null) {
            params.addValue("id_empresa_usuario", usuario.getId_empresa_usuario());
        } else {
            params.addValue("id_empresa_usuario", null);
        }

        return params;
    }


    @Override
    public List<Usuarios> findAll() {
        List<Usuarios> usuarios = List.of();
        try {
            usuarios = jdbcTemplate.query(sqlSelectAllUsers(), createUsuariosRowMapper());
            return usuarios;
        } catch (Exception e) {
            LOGGER.error("Houver um erro ao consultar os usuários: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Usuarios findById(UUID id_usuario) {
        List<Usuarios> usuarios;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_usuario", id_usuario);
            usuarios = jdbcTemplate.query(sqlSelectUserById(), params, createUsuariosRowMapper());
            return usuarios.isEmpty() ? null : usuarios.get(0);
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao consultar o  usuário: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean cadastrarUsuario(Usuarios usuario) {
        try {
            MapSqlParameterSource params = parameterSource(usuario);
            int numLinhasAfetadas = jdbcTemplate.update(sqlNewUser(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao cadastrar o usuário: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean modificarUsuario(Usuarios usuario) {
        try {
            MapSqlParameterSource params = parameterSource(usuario);
            int numLinhasAfetadas = jdbcTemplate.update(sqlUpdateUser(), params);
            return numLinhasAfetadas > 0;
        } catch (Exception e) {
            LOGGER.error("Houver um erro ao atualizar o usuário: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteUsuarioById(UUID id_usuario) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id_usuario", id_usuario);
            int numLinhasAfetas = jdbcTemplate.update(sqlDeleteUserById(), params);
            return numLinhasAfetas == 1;
        } catch (Exception e) {
            LOGGER.error("Houve um erro ao deletar o usuario: " + id_usuario + ". \n" + e.getMessage());
            throw e;
        }
    }
}
