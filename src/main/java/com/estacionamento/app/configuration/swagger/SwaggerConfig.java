package com.estacionamento.app.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIProject() {

        Contact contact = new Contact();

        contact.setEmail("joaodarwin.ip22@gmail.com");
        contact.setName("João Darwin");
        contact.setUrl("https://github.com/Joao-Darwin");

        License license = new License();

        license.setName("MIT License");
        license.setUrl("https://github.com/Joao-Darwin/estacionamento-restapi/blob/main/LICENSE");

        Info information = new Info()
                .contact(contact)
                .title("Parking API")
                .version("1.0.0")
                .description("API that allows users to manage company parking lots.")
                .license(license);

        return new OpenAPI().info(information);
    }
}
