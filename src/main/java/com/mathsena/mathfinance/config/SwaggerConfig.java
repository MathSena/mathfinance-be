package com.mathsena.mathfinance.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .components(componentsConfig())
            .info(apiInfo())
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
  }

  private Components componentsConfig() {
    return new Components()
            .addSecuritySchemes(
                    "bearerAuth",
                    new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
            );
  }

  private Info apiInfo() {
    return new Info()
            .title("MathFinance API")
            .version("1.0")
            .description("API documentation for the MathFinance project");
  }
}
