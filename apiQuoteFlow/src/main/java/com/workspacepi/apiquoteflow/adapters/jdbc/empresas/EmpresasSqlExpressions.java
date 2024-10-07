package com.workspacepi.apiquoteflow.adapters.jdbc.empresas;

public class EmpresasSqlExpressions {
    public static String sqlSelectAllEmpresas() {
        return """
               SELECT id_empresa,
                    cnpj_empresa,
                    email_empresa,
                    nome_empresa,
                    senha_empresa
               FROM empresa
            """;
    }

    public static String sqlSelectEmpresaById() {
        return """
               SELECT id_empresa,
                    cnpj_empresa,
                    email_empresa,
                    nome_empresa,
                    senha_empresa
               FROM empresa
               WHERE id_empresa = :id_empresa
           """;
    }

    public static String sqlNewEmpresa() {
        return """
                INSERT INTO empresa(
                    id_empresa,
                    cnpj_empresa,
                    email_empresa,
                    nome_empresa,
                    senha_empresa)
                    values (
                        :id_empresa,
                        :cnpj_empresa,
                        :email_empresa,
                        :nome_empresa,
                        :senha_empresa)
           """;
    }

    public static String sqlUpdateEmpresa() {
        return """
               UPDATE empresa
               SET id_empresa = :id_empresa,
                    cnpj_empresa = :cnpj_empresa,
                    email_empresa = :email_empresa,
                    nome_empresa = :nome_empresa,
                    senha_empresa = :senha_empresa
               WHERE id_empresa = :id_empresa
           """;
    }

    public static String sqlDeleteEmpresaById() {
        return """
                   DELETE FROM empresa WHERE id_empresa = :id_empresa
               """;
    }
}
