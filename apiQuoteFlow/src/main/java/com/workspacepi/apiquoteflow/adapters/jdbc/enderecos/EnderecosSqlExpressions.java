// Nosso arquivo para construir as nossas query em sql que serão usadas no JDBC repository
// Construíndo métodos staticos para que não seja necessário instânciar a classe

package com.workspacepi.apiquoteflow.adapters.jdbc.enderecos;

public class EnderecosSqlExpressions {
    public static String sqlSelectAllEnderecos() {
        return """
                    SELECT bairro,
                        cep,
                        complemento,
                        localidade,
                        logradouro,
                        numero,
                        uf,
                        id_empresa,
                        id_endereco
                    FROM endereco
               """;
    }

    public static String sqlSelectEnderecosById() {
        return """
                    SELECT bairro,
                        cep,
                        complemento,
                        localidade,
                        logradouro,
                        numero,
                        uf,
                        id_empresa,
                        id_endereco
                    FROM endereco
                    WHERE id_endereco = :id_endereco
                """;
    
}
    public static String sqlCadastrarEndereco() {
        return """
                    INSERT INTO endereco(
                        bairro,
                        cep,
                        complemento, 
                        localidade,
                        logradouro,
                        numero,
                        uf,
                        id_empresa,
                        id_endereco)
                        values (
                            :bairro,
                            :cep,
                            :complemento, 
                            :localidade,
                            :logradouro,
                            :numero,
                            :uf,
                            :id_empresa,
                            :id_endereco)    
                """;
    }

    public static String sqlModificarEndereco() {
        return """
                    UPDATE endereco
                    SET bairro = :bairro,
                        cep = :cep,
                        complemento = :complemento,
                        localidade = :localidade,
                        logradouro = :logradouro,
                        numero = :numero,
                        uf = :uf,
                        id_empresa = :id_empresa,
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
