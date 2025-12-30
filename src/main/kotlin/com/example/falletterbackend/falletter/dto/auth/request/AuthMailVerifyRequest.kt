package com.example.falletterbackend.falletter.dto.auth.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "이메일 인증코드 발송 요청")
data class AuthMailVerifyRequest(
    @Schema(description = "이메일", example = "user@example.com")
    val email: String
)
