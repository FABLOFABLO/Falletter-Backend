package com.example.falletterbackend.falletter.dto.admin.auth.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "어드민 로그인 요청")
data class AdminSignInRequest(
    @Schema(description = "이메일", example = "admin@dsm.hs.kr")
    val email: String,

    @Schema(description = "비밀번호", example = "!!password123")
    val password: String
)
