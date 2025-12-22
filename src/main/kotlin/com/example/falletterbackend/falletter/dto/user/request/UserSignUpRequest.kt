package com.example.falletterbackend.falletter.dto.user.request

import com.example.falletterbackend.falletter.entity.user.Gender
import com.example.falletterbackend.falletter.entity.user.Theme

data class UserSignUpRequest(
    val email: String,
    val password: String,
    val schoolNumber: String,
    val name: String,
    val gender: Gender,
    val theme: Theme,
    val profileImage: String?
)
