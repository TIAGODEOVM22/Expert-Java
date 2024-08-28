package br.com.tiago.user_service_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;

/*CLASSE DE CONFIGURAÇÃO DA DOCUMENTAÇÃO*/
@Configuration
public class OpenApiConfig {

    @BeanProperty
    public OpenAPI customOpenAPI(
            @Value(value = "${springdoc.openapi.title}") final String title,
            @Value(value = "${springdoc.openapi.description}") final String description,
            @Value(value = "${springdoc.openapi.version}") final String version){
        return new OpenAPI()
            .info(
                    new Info()
                            .title(title)
                            .description(description)
                            .version(version)
            );
    }
}
