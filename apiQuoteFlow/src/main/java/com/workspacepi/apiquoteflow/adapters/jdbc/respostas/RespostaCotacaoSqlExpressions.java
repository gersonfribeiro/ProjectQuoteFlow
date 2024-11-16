package com.workspacepi.apiquoteflow.adapters.jdbc.respostas;

public class RespostaCotacaoSqlExpressions {

    public static String sqlFindRespostasCotacaoByEmpresa() {
        return """
                SELECT * FROM resposta_cotacao WHERE id_empresa = :id_empresa
            """;
    }

    public static String sqlFindRespostaCotacaoByEmpresaAndCotacao() {
        return """
                SELECT * FROM resposta_cotacao WHERE id_empresa = :id_empresa AND id_cotacao = :id_cotacao
            """;
    }

    public static String sqlEnviarRespostaCotacao() {
        return """
                INSERT INTO resposta_cotacao(
                    id_resposta, data_resposta, id_empresa_resposta, id_cotacao
                )
                VALUES(
                    :id_resposta, :data_resposta, :id_empresa_resposta, :id_cotacao
                )
            """;
    }

    public static String sqlModificarResposta() {
        return """
                UPDATE resposta_cotacao
                SET
                    id_resposta = :id_resposta,
                    data_resposta = :data_resposta,
                    id_empresa_resposta = :id_empresa_resposta,
                    id_cotacao = :id_cotacao
                WHERE id_empresa = :id_empresa AND id_cotacao = :id_cotacao
            """;
    }

    public static String sqlRemoverResposta() {
        return """
                DELETE FROM resposta_cotacao WHERE id_empresa = :id_empresa AND id_cotacao = :id_cotacao
            """;
    }
}
