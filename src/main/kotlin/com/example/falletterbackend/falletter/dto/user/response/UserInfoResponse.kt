package com.example.falletterbackend.falletter.dto.user.response

import com.example.falletterbackend.falletter.entity.user.Gender

data class UserInfoResponse(
    val id: Long,
    val email: String,
    val schoolNumber: String,
    val name: String,
    val gender: Gender,
    val profileImage: String
)
