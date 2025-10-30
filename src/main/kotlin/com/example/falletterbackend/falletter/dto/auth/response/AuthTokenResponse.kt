package com.example.falletterbackend.falletter.dto.auth.response

data class AuthTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
