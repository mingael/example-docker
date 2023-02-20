package com.example.swagger.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(
        title = "API 명세서",
        description = "API Document",
        version = "v1",
        termsOfService = "http://swagger.io/terms/",
        contact = Contact(
            name = "migael",
            email = "owo@dico.me"
        ),
        license = License(
            name = "dico",
            url = "https://dico.me"
        )
    )
)
@Configuration
class SwaggerConfig {

    @Bean
    fun authApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("Auth API")
            .pathsToMatch("/api/auth/**")
            .build()

    @Bean
    fun accountApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("Account API")
            .packagesToScan("com.example.swagger.account")
//            .addOpenApiCustomiser(loginRequiredApiCustomizer())
            .build()

    private fun loginRequiredApiCustomizer() = OpenApiCustomiser { openApi ->
        openApi
            .addSecurityItem(SecurityRequirement().addList("Authorization"))
            .components(
                Components().addSecuritySchemes(
                    "Authorization",
                    SecurityScheme()
                        .`in`(SecurityScheme.In.HEADER)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                )
            )
    }

}