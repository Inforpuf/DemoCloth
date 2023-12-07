package com.example.demoCloth.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springClothOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(" Swagger DemoCloth - OpenAPI 3.0")
                        .description("This is a sample Cloth Store Server based on the OpenAPI 3.0 specification.")
                        .version("v0.0.2")
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation().description("Find out more about Swagger").url("http://swagger.io"));
    }
}
