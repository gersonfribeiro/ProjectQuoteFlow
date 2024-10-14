// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.produtos;

public class ProdutosSqlRepository {
//    implementação individual:
    public static String sqlSelectAllProdutos() {
        return """
                    SELECT * FROM produtos
                """;
    }

    public static String sqlSelectProdutoById() {
        return """
               SELECT * FROM produtos WHERE id_produto = :id_produto
               """;
    }

    public static String sqlCadastrarProduto() {
        return """
                     INSERT INTO produtos(
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
                    UPDATE produtos
                    SET categoriaProduto = :categoriaProduto,
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
                   DELETE FROM produtos WHERE id_produto = :id_produto
               """;
    }

//    Novas implementações:

    public static String sqlAllProdutosByEmpresa() {
        return """
                SELECT * FROM produtos WHERE id_empresa = :id_empresa
               """;
    }

    public static String sqlProdutoByIdAndEmpresa() {
        return """
                SELECT * FROM produtos WHERE id_produto = :id_produto AND id_empresa = :id_empresa
               """;
    }

    public static String sqlCadastrarProdutoInEmpresa() {
        return """
                 INSERT INTO produtos(
                     id_produto,
                     categoria,
                     descricao,
                     observacao,
                     sku,
                     variacao,
                     id_empresa)
                         values (
                             :id_produto,
                             :categoria,
                             :descricao,
                             :observacao,
                             :sku,
                             :variacao,
                             :id_empresa)
                """;
    }

    public static String sqlModificarProdutoInEmpresa() {
        return """
                UPDATE produtos
                    SET categoria = :categoria,
                        descricao = :descricao,
                        observacao = :observacao,
                        sku = :sku,
                        variacao = :variacao
                    WHERE id_produto = :id_produto AND id_empresa = :id_empresa
               """;
    }

    public static String sqlDeleteProdutoInEmpresa() {
        return """
                DELETE FROM produtos WHERE id_produto = :id_produto AND id_empresa = :id_empresa
               """;
    }
}
