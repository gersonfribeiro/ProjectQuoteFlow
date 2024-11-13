package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes.produtos;

public class ProdutosCotacaoSqlExpressions {

    public static String sqlFindAllProdutosByCotacao() {
        return """
            SELECT * FROM cotacao_produtos WHERE id_cotacao = :id_cotacao
        """;
    }

    public static String sqlFindProdutoByCotacaoAndId() {
        return """
            SELECT * FROM cotacao_produtos WHERE id_cotacao = :id_cotacao AND id_produto = :id_produto
        """;
    }

    public static String sqlInserirProdutosCotacao() {
        return """
            INSERT INTO cotacao_produtos(
                id,
                id_cotacao,
                id_produto,
                quantidade)
            VALUES(
                :id,
                :id_cotacao,
                :id_produto,
                :quantidade)
        """;
    }

    public static String sqlModificarProdutosCotacao() {
        return """
            UPDATE cotacao_produtos
            SET id = :id,
                id_cotacao = :id_cotacao,
                id_produto = :id_produto,
                quantidade = :quantidade
            WHERE id_cotacao = :id_cotacao AND id_produto = :id_produto
        """;
    }

    public static String sqlDeleteProdutosByCotacaoAndId() {
        return """
            DELETE FROM cotacao_produtos WHERE id_cotacao = :id_cotacao AND id_produto = :id_produto
        """;
    }

}
