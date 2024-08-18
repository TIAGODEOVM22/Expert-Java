package br.com.tiago.user_service_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*CLASSE DE CONFIGURAÇÃO DA DOCUMENTAÇÃO*/
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPIApi(
            @Value("${springdoc.openapi.title}") final String title,
            @Value("${springdoc.openapi.description}") final String description,
            @Value("${springdoc.openapi.version}") final String version
    ){
        return new OpenAPI()
            .info(
                    new Info()
                            .title(title)
                            .description(description)
                            .version(version)
            );
    }
}
