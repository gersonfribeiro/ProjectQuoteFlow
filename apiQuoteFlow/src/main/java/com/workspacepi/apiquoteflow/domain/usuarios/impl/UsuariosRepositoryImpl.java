package com.workspacepi.apiquoteflow.domain.usuarios.impl;

import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import com.workspacepi.apiquoteflow.domain.usuarios.UsuariosRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuariosRepositoryImpl implements UsuariosRepository {

    private final JdbcTemplate jdbcTemplate;

    // Injetar o JdbcTemplate através do construtor
    public UsuariosRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuarios> usuarioRowMapper = new RowMapper<Usuarios>() {
        @Override
        public Usuarios mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Usuarios(
                    UUID.fromString(rs.getString("id_usuario")),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"), // Corrigido para 'telefone'
                    UUID.fromString(rs.getString("id_empresa")) // Corrigido para 'id_empresa'
            );
        }
    };

    @Override
    public List<Usuarios> findAll() {
        String sql = "SELECT * FROM usuarios";
        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    @Override
    public Usuarios findById(UUID id_usuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_usuario.toString()}, usuarioRowMapper);
    }

    @Override
    public Boolean cadastrarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (id_usuario, nome, email, senha, telefone, id_empresa) VALUES (?, ?, ?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql, usuario.getId_usuario().toString(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTelefone(), usuario.getId_empresa().toString());
        return rows > 0;
    }

    @Override
    public Boolean modificarUsuario(Usuarios usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, telefone = ?, id_empresa = ? WHERE id_usuario = ?";
        int rows = jdbcTemplate.update(sql, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTelefone(), usuario.getId_empresa().toString(), usuario.getId_usuario().toString());
        return rows > 0;
    }

    @Override
    public Boolean deleteUsuarioById(UUID id_usuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        int rows = jdbcTemplate.update(sql, id_usuario.toString());
        return rows > 0;
    }
}
