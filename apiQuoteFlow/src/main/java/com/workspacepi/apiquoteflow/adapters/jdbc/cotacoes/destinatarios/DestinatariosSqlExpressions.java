package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes.destinatarios;

public class DestinatariosSqlExpressions {

    public static String sqlFindAllDestinatariosByCotacao(){
        return """
            SELECT * FROM cotacao_empresa_destinataria WHERE id_cotacao = :id_cotacao
        """;
    }

    public static String sqlFindEmpresaDestinatariaByCotacaoAndId(){
        return """
            SELECT * FROM cotacao_empresa_destinataria WHERE id_cotacao = :id_cotacao AND id_destinatario = :id_destinatario
        """;
    }

    public static String sqlInserirDestinatario(){
        return """
            INSERT INTO cotacao_empresa_destinataria(
                id,
                data_envio,
                id_cotacao,
                id_destinatario)
            VALUES(
                :id,
                :data_envio,
                :id_cotacao,
                :id_destinatario)
        """;
    }

    public static String sqlModificarDestinatario(){
        return """
            UPDATE cotacao_empresa_destinataria
            SET id = :id,
                data_envio = :data_envio,
                id_cotacao = :id_cotacao,
                id_destinatario = :id_destinatario
            WHERE id_cotacao = :id_cotacao AND id_destinatario = :id_destinatario
        """;
    }

    public static String sqlRemoverDestinatario(){
        return """
            DELETE * FROM cotacao_empresa_destinataria WHERE id_cotacao = :id_cotacao AND id_destinatario = :id_destinatario
        """;
    }

}
