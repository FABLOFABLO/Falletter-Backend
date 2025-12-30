package com.example.falletterbackend.common.config.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.tags.Tag
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    private val tagOrder = listOf(
        "Auth",
        "User",
        "Admin User",
        "Admin Community",
        "Admin Letter",
        "Admin Notice",
        "Answer",
        "Community",
        "Comment",
        "Item",
        "History",
        "Letter",
        "Hint",
        "Question"
    )

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

    @Bean
    fun sortTagsAlphabetically(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi ->
            val tagDescriptions = mapOf(
                "Auth" to "인증 API",
                "User" to "사용자 API",
                "Admin User" to "관리자 학생 관리 API",
                "Admin Community" to "관리자 커뮤니티 관리 API",
                "Admin Letter" to "관리자 편지 관리 API",
                "Admin Notice" to "관리자 공지사항 API",
                "Answer" to "답변 API",
                "Community" to "커뮤니티 게시판 API",
                "Comment" to "댓글 API",
                "Item" to "아이템 API",
                "History" to "벽돌 사용 기록 API",
                "Letter" to "편지 API",
                "Hint" to "힌트 API",
                "Question" to "질문 API"
            )

            val sortedTags = tagOrder.map { name ->
                Tag().name(name).description(tagDescriptions[name] ?: "")
            }

            openApi.tags(sortedTags)
        }
    }
}
