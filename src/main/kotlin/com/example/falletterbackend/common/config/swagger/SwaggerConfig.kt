package com.example.falletterbackend.common.config.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.tags.Tag
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
            .tags(listOf(
                // 사용자/인증 관련
                Tag().name("User").description("사용자 API"),
                Tag().name("Auth").description("인증 API"),

                // 커뮤니티/댓글 관련
                Tag().name("Community").description("커뮤니티 게시판 API"),
                Tag().name("Comment").description("댓글 API"),

                // 편지 관련
                Tag().name("Letter").description("편지 API"),

                // Q&A 관련
                Tag().name("Question").description("질문 API"),
                Tag().name("Answer").description("답변 API"),
                Tag().name("Hint").description("힌트 API"),

                // 아이템 관련
                Tag().name("Item").description("아이템 API"),
                Tag().name("History").description("벽돌 사용 기록 API"),

                // 관리자 관련
                Tag().name("Admin Letter").description("관리자 편지 관리 API"),
                Tag().name("Admin Notice").description("관리자 공지사항 API"),
                Tag().name("Admin Community").description("관리자 커뮤니티 관리 API")
            ))
            .components(
                Components().addSecuritySchemes("Bearer Token", securityScheme)
            )
            .addSecurityItem(securityRequirement)
    }
}
