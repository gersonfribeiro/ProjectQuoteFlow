// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes;

public class CotacoesSqlExpressions {
    public static String sqlSelectAllQuotations() {
        return """
                    SELECT id_cotacao,
                        data,
                        numero_cotacao,
                        status,
                        id_empresa
                    FROM cotacao
                """;
    }

    public static String sqlSelectQuotationById() {
        return """
                    SELECT id_cotacao,
                        data,
                        numero,
                        status,
                        id_empresa
                    FROM cotacao
                    WHERE id_cotacao = :id_cotacao
                """;
    }

    public static String sqlSolicitarCotacao() {
        return """
                     INSERT INTO cotacao(
                            id_cotacao,
                            data,
                            numero,
                            status,
                            id_empresa)
                         values (
                            :id_cotacao,
                            :data,
                            :numero,
                            :status,
                            :id_empresa)
                """;
    }

    public static String sqlModificarCotacao() {
        return """
                    UPDATE cotacao
                    SET id_cotacao = :id_cotacao,
                        data = :data,
                        numero_cotacao = :numero_cotacao,
                        status = :status,
                        id_empresa  = :id_empresa
                    WHERE id_cotacao = :id_cotacao
                """;
    }

    public static String sqlDeleteCotacaoById() {
        return """
                   DELETE FROM cotacao WHERE id_cotacao = :id_cotacao
               """;
    }
}
