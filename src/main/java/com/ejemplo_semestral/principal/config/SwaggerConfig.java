package com.ejemplo_semestral.principal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
        .title("Api Micro Servicios seccion 009 por Gabriel  ")
        .version("1.0")
        .description("Este es el micro servicio de Usuarios "));
    }
    
}
