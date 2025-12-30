package com.example.falletterbackend.falletter.dto.user.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인 요청")
data class UserSignInRequest(
    @Schema(description = "이메일", example = "user@example.com")
    val email: String,

    @Schema(description = "비밀번호", example = "password123")
    val password: String
)
