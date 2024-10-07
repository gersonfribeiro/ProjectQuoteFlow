// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.enderecos;

public class EnderecosSqlExpressions {
    public static String sqlSelectAllEnderecos() {
        return """
                    SELECT bairro_endereco,
                        cep_endereco,
                        complemento_endereco, 
                        localidade_endereco,
                        logradouro_endereco,
                        numero_endereco,
                        uf_endereco,
                        id_empresa_endereco,
                        id_endereco
                    FROM endereco
                """;
    }

    public static String sqlSelectEnderecosById() {
        return """
                    SELECT bairro_endereco,
                        cep_endereco,
                        complemento_endereco, 
                        localidade_endereco,
                        logradouro_endereco,
                        numero_endereco,
                        uf_endereco,
                        id_empresa_endereco,
                        id_endereco
                    FROM endereco
                    WHERE id_endereco = :id_endereco
                """;
    
}
    public static String sqlCadastrarEndereco() {
        return """
                    INSERT INTO endereco(
                        bairro_endereco,
                        cep_endereco,
                        complemento_endereco, 
                        localidade_endereco,
                        logradouro_endereco,
                        numero_endereco,
                        uf_endereco,
                        id_empresa_endereco,
                        id_endereco)
                        values (
                            :bairro_endereco,
                            :cep_endereco,
                            :complemento_endereco, 
                            :localidade_endereco,
                            :logradouro_endereco,
                            :numero_endereco,
                            :uf_endereco,
                            :id_empresa_endereco,
                            :id_endereco)    
                """;
    }

    public static String sqlModificarEndereco() {
        return """
                    UPDATE endereco
                    SET bairro_endereco = :bairro_endereco,
                        cep_endereco = :cep_endereco,
                        complemento_endereco = :complemento_endereco,
                        localidade_endereco = :localidade_endereco,
                        logradouro_endereco = :logradouro_endereco,
                        numero_endereco = :numero_endereco,
                        uf_endereco = :uf_endereco,
                        id_empresa_endereco = :id_empresa_endereco,
                        id_endereco = :id_endereco
                    WHERE id_endereco = :id_endereco
                """;
    }

    public static String sqlDeleteEnderecoById() {
        return """
                   DELETE FROM endereco WHERE id_endereco = :id_endereco
               """;
    }
}
