package com.seek.pruebatecnica.infrastructure.configurations.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prueba Técnica Seek")
                        .contact(new Contact()
                                .name("Yacson Ramirez")
                                .url("https://www.linkedin.com/in/yacson3287/"))
                        .version("1.0.0")
                        .description("Backend Java SpringBoot 2024")

                )
                .servers(Collections.singletonList(new Server().url("http://localhost:8080")));
    }







}



