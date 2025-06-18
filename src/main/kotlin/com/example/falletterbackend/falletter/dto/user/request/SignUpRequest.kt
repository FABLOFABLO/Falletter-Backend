package com.example.falletterbackend.falletter.dto.user.request

import com.example.falletterbackend.falletter.entity.user.Gender

data class SignUpRequest(
    val email: String,
    val password: String,
    val schoolNumber: String,
    val gender: Gender,
    val profileImage: String?
)