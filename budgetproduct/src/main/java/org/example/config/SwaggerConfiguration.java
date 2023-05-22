package org.example.config;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Trevo api Quarkus",
                version = "3.0.1",
                description = "Este projeto foi criado com intuito efetuar cadastros e consultas de produtos oferecidos pela industria Trevo." +
                        " Com esta api, os usuários poderão consultar informações sobre os produtos." +
                        " Possibilitando a requisição de orçamentos dos produtos disponibilizados pela empresa.",
                contact = @Contact(
                        name = "Kaique Queiros github",
                        url = "https://github.com/KaiqueQueiros/Quarkus-Api",
                        email = "kaique.qhotmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)

public class SwaggerConfiguration extends Application {
}
