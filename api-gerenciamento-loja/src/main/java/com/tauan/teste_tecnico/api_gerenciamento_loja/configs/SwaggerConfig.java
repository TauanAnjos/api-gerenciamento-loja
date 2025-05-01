package com.tauan.teste_tecnico.api_gerenciamento_loja.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI projetoToDoList() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gerenciamento de loja")
                        .description("Projeto desenvolvido para teste técnico")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório GitHub")
                        .url("https://github.com/TauanAnjos/api-gerenciamento-loja"))
                // Configura o suporte ao esquema de autenticação via token Bearer
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                // Define que a autenticação é obrigatória para rotas protegidas
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
