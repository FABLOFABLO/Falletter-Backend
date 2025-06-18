package com.example.falletterbackend.falletter.dto.user.request

import com.example.falletterbackend.falletter.entity.user.Gender

data class UserSignUpRequest(
    val email: String,
    val password: String,
    val schoolNumber: String,
    val name: String,
    val gender: Gender,
    val profileImage: String?
)