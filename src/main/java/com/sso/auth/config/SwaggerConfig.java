package com.sso.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Authentication Service Open API")
                        .version("v-1.0.0")
                        .description("Authentication Service is a critical component in any application that manages user access and security. " +
                                "It is responsible for verifying the identity of users or systems attempting to access resources, ensuring that only authorized entities can proceed. " +
                                "This service typically involves processes like user login, token generation, and validation.")
                        .contact(new Contact()
                                .name("Ashiqul Islam Shajal")
                                .email("ashiqulislamshajal.cse@gmail.com")
                                .url("https://shajal.pro"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/auth/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/auth/v1/health")
                .build();
    }
}
