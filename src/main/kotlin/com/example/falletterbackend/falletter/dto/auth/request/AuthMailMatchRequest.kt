package com.example.falletterbackend.falletter.dto.auth.request

data class AuthMailMatchRequest(
    val email: String,
    val verifyCode: String
)