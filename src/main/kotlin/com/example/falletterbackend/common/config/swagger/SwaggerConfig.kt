package com.example.falletterbackend.common.config.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val securityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .name("Authorization")

        val securityRequirement = SecurityRequirement().addList("Bearer Token")

        return OpenAPI()
            .info(
                Info()
                    .title("Falletter API")
                    .description("Falletter Backend API Documentation")
                    .version("1.0.0")
            )
            .components(
                Components().addSecuritySchemes("Bearer Token", securityScheme)
            )
            .addSecurityItem(securityRequirement)
    }
}
