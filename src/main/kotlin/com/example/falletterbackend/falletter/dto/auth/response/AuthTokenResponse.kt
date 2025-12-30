package com.example.falletterbackend.falletter.dto.auth.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "인증 토큰 응답")
data class AuthTokenResponse(
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    val accessToken: String,

    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    val refreshToken: String
)
