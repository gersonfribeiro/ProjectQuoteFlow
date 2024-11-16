package com.workspacepi.apiquoteflow.adapters.jdbc.respostas.produtos;

public class RespostaProdutosSqlExpressions {

    public static String sqlFindRespostasProdutos() {
        return """
            SELECT * FROM resposta_produto WHERE id_resposta = :id_resposta
        """;
    }

    public static String sqlFindRespostaProdutos() {
        return """
            SELECT * FROM resposta_produto WHERE id_resposta = :id_resposta AND id_produto = :id_produto
        """;
    }

    public static String sqlEnviarResposta() {
        return """
            INSERT INTO resposta_produto(
                id,
                id_resposta,
                id_produto,
                preco,
                observacao
            )
            VALUES(
                :id
                :id_resposta
                :id_produto
                :preco
                :observacao
            )
        """;
    }

    public static String sqlModificarResposta() {
        return """
            UPDATE resposta_produto(
            SET
                id_resposta = :id_resposta
                id_produto = :id_produto
                preco = :preco
                observacao = :observacao
        """;
    }

    public static String sqlRemoverResposta() {
        return """
            DELETE FROM resposta_produto WHERE id_resposta = :id_resposta AND id_produto = :id_produto
        """;
    }
}
