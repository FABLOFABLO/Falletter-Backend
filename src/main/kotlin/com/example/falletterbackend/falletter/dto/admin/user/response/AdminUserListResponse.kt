package com.example.falletterbackend.falletter.dto.admin.user.response

import com.example.falletterbackend.falletter.entity.user.enums.Gender

data class AdminUserListResponse(
    val id: Long,
    val schoolNumber: String,
    val name: String,
    val profileImage: String?,
    val gender: Gender,
    val warningCount: Long
)
