package com.workspacepi.apiquoteflow.adapters.jdbc.usuarios;

public class UsuariosSqlExpressions {
    public static String sqlSelectAllUsers() {
        return """
               SELECT id_usuario,
                    email,
                    nome,
                    senha,
                    telefone,
                    id_empresa
               FROM usuarios
            """;
    }

    public static String sqlSelectUserById() {
        return """
               SELECT id_usuario,
                    email,
                    nome,
                    senha,
                    telefone,
                    id_empresa
               FROM usuarios
               WHERE id_usuario = :id_usuario
           """;
    }

    public static String sqlNewUser() {
        return """
                INSERT INTO usuarios(
                    id_usuario,
                    email,
                    nome,
                    senha)
                    values (
                        :id_usuario,
                        :email,
                        :nome,
                        :senha)
           """;
    }

    public static String sqlUpdateUser() {
        return """
               UPDATE usuarios
               SET email = :email,
                    nome = :nome,
                    senha = :senha,
                    telefone = :telefone,
                    id_empresa = :id_empresa
               WHERE id_usuario = :id_usuario
           """;
    }

    public static String sqlDeleteUserById() {
        return """
                   DELETE FROM usuarios WHERE id_usuario = :id_usuario
               """;
    }
}
