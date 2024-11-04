package com.workspacepi.apiquoteflow.adapters.jdbc.empresas;

public class EmpresasSqlExpressions {
    public static String sqlSelectAllEmpresas() {
        return """
               SELECT id_empresa,
                    cnpj,
                    email,
                    nome,
                    telefone
               FROM empresas
            """;
    }

    public static String sqlSelectEmpresaById() {
        return """
               SELECT id_empresa,
                    cnpj,
                    email,
                    nome,
                    telefone
               FROM empresas
               WHERE id_empresa = :id_empresa
           """;
    }

    public static String sqlSelectEmpresaByCnpj() {
        return """
               SELECT id_empresa,
                    cnpj,
                    email,
                    nome,
                    telefone
               FROM empresas
               WHERE cnpj = :cnpj
           """;
    }

    public static String sqlSelectEmpresaByEmail() {
        return """
               SELECT id_empresa,
                    cnpj,
                    email,
                    nome,
                    telefone
               FROM empresas
               WHERE email = :email
           """;
    }

    public static String sqlNewEmpresa() {
        return """
                INSERT INTO empresas(
                    id_empresa,
                    cnpj,
                    email,
                    nome,
                    telefone)
                    values (
                        :id_empresa,
                        :cnpj,
                        :email,
                        :nome,
                        :telefone)
           """;
    }

    public static String sqlUpdateEmpresa() {
        return """
               UPDATE empresas
               SET id_empresa = :id_empresa,
                    cnpj = :cnpj,
                    email = :email,
                    nome = :nome,
                    telefone = :telefone
               WHERE id_empresa = :id_empresa
           """;
    }

    public static String sqlDeleteEmpresaById() {
        return """
                   DELETE FROM empresas WHERE id_empresa = :id_empresa
               """;
    }
}
