// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.cotacoes;

public class CotacoesSqlExpressions {
    public static String sqlSelectAllQuotations() {
        return """
                    SELECT id_cotacao,
                        data_cotacao,
                        numero_cotacao,
                        status_cotacao,
                        id_empresa_cotacao
                    FROM cotacao
                """;
    }

    public static String sqlSelectQuotationById() {
        return """
                    SELECT id_cotacao,
                        data_cotacao,
                        numero_cotacao,
                        status_cotacao,
                        id_empresa_cotacao
                    FROM cotacao
                    WHERE id_cotacao = :id_cotacao
                """;
    }

    public static String sqlSolicitarCotacao() {
        return """
                     INSERT INTO cotacao(
                            id_cotacao,
                            data_cotacao,
                            numero_cotacao,
                            status_cotacao,
                            id_empresa_cotacao)
                         values (
                            :id_cotacao,
                            :data_cotacao,
                            :numero_cotacao,
                            :status_cotacao,
                            :id_empresa_cotacao)
                """;
    }

    public static String sqlModificarCotacao() {
        return """
                    UPDATE cotacao
                    SET id_cotacao = :id_cotacao,
                        data_cotacao = :data_cotacao,
                        numero_cotacao = :numero_cotacao,
                        status_cotacao = :status_cotacao,
                        id_empresa_cotacao  = :id_empresa_cotacao
                    WHERE id_cotacao = :id_cotacao
                """;
    }

    public static String sqlDeleteCotacaoById() {
        return """
                   DELETE FROM cotacao WHERE id_cotacao = :id_cotacao
               """;
    }
}
