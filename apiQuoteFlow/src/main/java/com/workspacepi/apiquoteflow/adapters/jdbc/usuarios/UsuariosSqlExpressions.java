package com.workspacepi.apiquoteflow.adapters.jdbc.usuarios;

public class UsuariosSqlExpressions {
    public static String sqlSelectAllUsers() {
        return """
               SELECT id_usuario,
                    email_usuario,
                    nome_usuario,
                    senha_usuario,
                    telefone_usuario,
                    id_empresa_usuario
               FROM usuarios
            """;
    }

    public static String sqlSelectUserById() {
        return """
               SELECT id_usuario,
                    email_usuario,
                    nome_usuario,
                    senha_usuario,
                    telefone_usuario,
                    id_empresa_usuario
               FROM usuarios
               WHERE id_usuario = :id_usuario
           """;
    }

    public static String sqlNewUser() {
        return """
                INSERT INTO usuarios(
                    id_usuario,
                    email_usuario,
                    nome_usuario,
                    senha_usuario,
                    telefone_usuario,
                    id_empresa_usuario)
                    values (
                        :id_usuario,
                        :email_usuario,
                        :nome_usuario,
                        :senha_usuario,
                        :telefone_usuario,
                        :id_empresa_usuario)
           """;
    }

    public static String sqlUpdateUser() {
        return """
               UPDATE usuarios
               SET email_usuario = :email_usuario,
                    nome_usuario = :nome_usuario,
                    senha_usuario = :senha_usuario,
                    telefone_usuario = :telefone_usuario,
                    id_empresa_usuario = :id_empresa_usuario
               WHERE id_usuario = :id_usuario
           """;
    }

    public static String sqlDeleteUserById() {
        return """
                   DELETE FROM usuarios WHERE id_usuario = :id_usuario
               """;
    }
}
