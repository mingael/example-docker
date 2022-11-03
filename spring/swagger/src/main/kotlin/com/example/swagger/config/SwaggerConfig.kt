package com.example.swagger.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(
        title = "API 명세서",
        description = "API Document",
        version = "v1",
        contact = Contact(
            name = "migael",
            email = "owo@dico.me"
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
            .build()

}