package com.example.falletterbackend.common.config.swagger

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import io.swagger.v3.oas.models.tags.Tag
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
    private val objectMapper: ObjectMapper
) {

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
            .servers(
                listOf(
                    Server()
                        .url("http://localhost:8080")
                        .description("dev"),
                    Server()
                        .url("https://falletter.co.kr")
                        .description("prod")
                )
            )
            .components(
                Components().addSecuritySchemes("Bearer Token", securityScheme)
            )
            .addSecurityItem(securityRequirement)
    }

    @Bean
    fun modelResolver(): ModelResolver {
        return ModelResolver(objectMapper)
    }

    private val tagOrder = listOf(
        "auth",
        "user",
        "answer",
        "community",
        "comment",
        "item",
        "history",
        "letter",
        "hint",
        "question",
        "timer",
        "suspend",
        "notice",
        "fcm_device",
        "notification",
        "Admin User",
        "Admin Community",
        "Admin Letter",
        "Admin Notice",
    )

    @Bean
    fun sortTagsAlphabetically(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi ->
            val tagDescriptions = mapOf(
                "auth" to "인증 API",
                "user" to "사용자 API",
                "answer" to "답변 API",
                "community" to "커뮤니티 게시판 API",
                "comment" to "댓글 API",
                "item" to "아이템 API",
                "history" to "벽돌 사용 기록 API",
                "letter" to "편지 API",
                "hint" to "힌트 API",
                "question" to "질문 API",
                "timer" to "타이머 API",
                "suspend" to "경고/정지 내역 API",
                "notice" to "공지사항 API",
                "fcm_device" to "디바이스 토큰 API",
                "notification" to "알림 설정 API",
                "Admin User" to "관리자 학생 관리 API",
                "Admin Community" to "관리자 커뮤니티 관리 API",
                "Admin Letter" to "관리자 편지 관리 API",
                "Admin Notice" to "관리자 공지사항 API",
            )

            val sortedTags = tagOrder.map { name ->
                Tag().name(name).description(tagDescriptions[name] ?: "")
            }

            openApi.tags(sortedTags)
        }
    }
}
