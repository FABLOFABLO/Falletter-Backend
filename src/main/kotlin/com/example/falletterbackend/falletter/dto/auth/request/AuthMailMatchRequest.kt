package com.example.falletterbackend.falletter.dto.auth.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "이메일 인증코드 확인 요청")
data class AuthMailMatchRequest(
    @Schema(description = "이메일", example = "user@dsm.hs.kr")
    val email: String,

    @Schema(description = "인증코드", example = "123456")
    val verifyCode: String
)
