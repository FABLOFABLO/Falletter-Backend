package com.example.falletterbackend.falletter.dto.auth.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)