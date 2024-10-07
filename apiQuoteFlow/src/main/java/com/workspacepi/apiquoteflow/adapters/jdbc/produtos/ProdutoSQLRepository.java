// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.produtos;

public class ProdutoSQLRepository {
    public static String sqlSelectAllProdutos() {
        return """
                    SELECT categoria_produto,
                        descricao_produto,
                        observacao_produto,
                        sku_produto,
                        id_empresa_produto,
                        id_produto
                    FROM produto
                """;
    }

    public static String sqlSelectProdutoById() {
        return """
                    SELECT categoria_produto,
                         descricao_produto,
                         observacao_produto,
                         sku_produto,
                         id_empresa_produto,
                         id_produto
                    FROM produto
                    WHERE id_produto = :id_produto
                """;
    }

    public static String sqlCadastrarProduto() {
        return """
                     INSERT INTO produto(
                         categoria_produto,
                         descricao_produto,
                         observacao_produto,
                         sku_produto,
                         id_empresa_produto,
                         id_produto)
                         values (
                             :categoria_produto,
                             :descricao_produto,
                             :observacao_produto,
                             :sku_produto,
                             :id_empresa_produto,
                             :id_produto)
                """;
    }

    public static String sqlModificarProduto() {
        return """
                    UPDATE produto
                    SET categoria_produto = :categoria_produto,
                         descricao_produto = :descricao_produto,
                         observacao_produto = :observacao_produto,
                         sku_produto = sku_produto,
                         id_empresa_produto = :id_empresa_produto,
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
