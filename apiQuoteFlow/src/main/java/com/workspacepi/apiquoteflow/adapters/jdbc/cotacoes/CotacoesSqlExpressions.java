// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes;

public class CotacoesSqlExpressions {
    public static String sqlSelectAllQuotations() {
        return """
                    SELECT * FROM cotacoes
                """;
    }

    public static String sqlSelectQuotationById() {
        return """
                    SELECT * FROM cotacoes
                    WHERE id_cotacao = :id_cotacao
                """;
    }

    public static String sqlSolicitarCotacao() {
        return """
                     INSERT INTO cotacoes(
                            id_cotacao,
                            data,
                            numero,
                            status,
                            id_empresa)
                         VALUES(
                            :id_cotacao,
                            :data,
                            :numero,
                            :status,
                            :id_empresa)
                """;
    }

    public static String sqlModificarCotacao() {
        return """
                    UPDATE cotacoes
                    SET id_cotacao = :id_cotacao,
                        data = :data,
                        numero = :numero,
                        status = :status,
                        id_empresa  = :id_empresa
                    WHERE id_cotacao = :id_cotacao
                """;
    }

    public static String sqlDeleteCotacaoById() {
        return """
                   DELETE FROM cotacoes WHERE id_cotacao = :id_cotacao
               """;
    }
}
