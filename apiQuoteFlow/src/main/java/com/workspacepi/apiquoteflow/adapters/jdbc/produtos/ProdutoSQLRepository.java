// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.produtos;

public class ProdutoSQLRepository {
    public static String sqlSelectAllProdutos() {
        return """
                    SELECT categoria,
                        descricao,
                        observacao,
                        sku,
                        id_empresa,
                        id_produto
                    FROM produto
                """;
    }

    public static String sqlSelectProdutoById() {
        return """
                    SELECT categoria,
                         descricao,
                         observacao,
                         sku,
                         id_empresa,
                         id_produto
                    FROM produto
                    WHERE id_produto = :id_produto
                """;
    }

    public static String sqlCadastrarProduto() {
        return """
                     INSERT INTO produto(
                         categoria,
                         descricao,
                         observacao,
                         sku,
                         id_empresa,
                         id_produto)
                         values (
                             :categoria,
                             :descricao,
                             :observacao,
                             :sku,
                             :id_empresa,
                             :id_produto)
                """;
    }

    public static String sqlModificarProduto() {
        return """
                    UPDATE produto
                    SET categoria = :categoria,
                         descricao = :descricao,
                         observacao = :observacao,
                         sku = sku,
                         id_empresa = :id_empresa,
                         id_produto = :id_produto
                    WHERE id_produto = :id_produto
                """;
    }

    public static String sqlDeleteProdutoById() {
        return """
                   DELETE FROM produto WHERE id_produto = :id_produto
               """;
    }
    
}
