package com.workspacepi.apiquoteflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para a documentação da API utilizando Swagger.
 * A documentação pode ser acessada pelo link: http://localhost:8080/swagger-ui/index.html#/
 */
@Configuration  // Indica que esta classe contém configurações de aplicação
public class SwaggerConfig {

    /**
     * Método que cria e configura um objeto OpenAPI com informações da API.
     *
     * @return Um objeto OpenAPI contendo as informações da API.
     */
    @Bean  // Indica que este método retorna um bean gerenciado pelo Spring
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()  // Cria uma nova instância de Info para definir os metadados da API
                        .title("Spring Boot REST API")  // Define o título da API
                        .description("Projeto QuoteFlow")  // Descrição da API
                        .version("1.0.0")  // Define a versão da API
                );
    }
}
