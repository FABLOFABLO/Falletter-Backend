package com.example.falletterbackend.falletter.dto.admin.user.request

data class AdminUserBlockRequest(
    val days: Int,
    val reason: String
)
