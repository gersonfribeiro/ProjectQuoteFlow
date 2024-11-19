package com.workspacepi.apiquoteflow.adapters.jdbc.respostas;

public class RespostaCotacaoSqlExpressions {

    public static String sqlBuscarCotacoesComProdutos() {
        return """
                SELECT
                    ced.id AS id_destinatario,
                    ced.data_envio,
                    ced.id_cotacao,
                    c.status,
                    cp.id_produto,
                    p.categoria,
                    p.descricao,
                    p.observacao,
                    p.variacao,
                    cp.quantidade
                FROM
                    cotacao_empresa_destinataria ced
                JOIN
                    cotacoes c ON ced.id_cotacao = c.id_cotacao
                JOIN
                    cotacao_produtos cp ON c.id_cotacao = cp.id_cotacao
                JOIN
                    produtos p ON cp.id_produto = p.id_produto
                WHERE
                    ced.id_destinatario = :id_destinatario
                AND
                    c.status = 'RESPOSTA_PENDENTE'
                ORDER BY
                    ced.id_cotacao, cp.id_produto;
            """;
    }

    public static String sqlFindRespostasCotacaoByEmpresa() {
        return """
                SELECT * FROM resposta_cotacao
                WHERE id_empresa_resposta = :id_empresa_resposta
            """;
    }

    public static String sqlFindRespostaCotacaoByEmpresaAndCotacao() {
        return """
                SELECT * FROM resposta_cotacao
                WHERE id_empresa_resposta = :id_empresa_resposta AND id_cotacao = :id_cotacao
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
                WHERE id_empresa_resposta = :id_empresa_resposta AND id_cotacao = :id_cotacao
            """;
    }

    public static String sqlRemoverResposta() {
        return """
                DELETE FROM resposta_cotacao
                WHERE id_empresa_resposta = :id_empresa_resposta AND id_cotacao = :id_cotacao
            """;
    }
}
