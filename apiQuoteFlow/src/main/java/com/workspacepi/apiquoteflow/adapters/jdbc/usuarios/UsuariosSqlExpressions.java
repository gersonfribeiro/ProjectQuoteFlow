package com.workspacepi.apiquoteflow.adapters.jdbc.usuarios;

public class UsuariosSqlExpressions {
    public static String sqlSelectAllUsers() {
        return """
               SELECT * FROM usuarios
            """;
    }

    public static String sqlSelectUserById() {
        return """
               SELECT * FROM usuarios WHERE id_usuario = :id_usuario
           """;
    }

    public static String sqlSelectUserByEmail() {
        return """
               SELECT * FROM usuarios WHERE email = :email
           """;
    }

    public static String sqlNewUser() {
        return """
                INSERT INTO usuarios(
                    id_usuario,
                    email,
                    nome,
                    senha,
                    permissao)
                    values (
                        :id_usuario,
                        :email,
                        :nome,
                        :senha,
                        :permissao)
           """;
    }

    public static String sqlUpdateUser() {
        return """
               UPDATE usuarios
               SET email = :email,
                    nome = :nome,
                    senha = :senha,
                    telefone = :telefone,
                    id_empresa = :id_empresa,
                    permissao = :permissao
               WHERE id_usuario = :id_usuario
           """;
    }

    public static String sqlDeleteUserById() {
        return """
               DELETE FROM usuarios WHERE id_usuario = :id_usuario
           """;
    }
}
