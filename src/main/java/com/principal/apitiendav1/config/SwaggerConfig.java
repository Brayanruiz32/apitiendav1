package com.principal.apitiendav1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
        .info(new Info()
            .title("API DE TIENDA")
            .description("Es una api de prueba que hizo el autor del mismo, aún esta en proceso de mejoras")
            .version("1.0.0")
            );
    }

}
