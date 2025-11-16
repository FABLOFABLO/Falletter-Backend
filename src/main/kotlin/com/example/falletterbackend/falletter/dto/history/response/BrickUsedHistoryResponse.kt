package com.example.falletterbackend.falletter.dto.history.response

import com.example.falletterbackend.falletter.entity.user.Gender
import java.time.LocalDateTime

data class BrickUsedHistoryResponse(
    val id: Long,
    val questionId: Long,
    val targetUserId: Long,
    val writerId: Long,
    val gender: Gender,
    val schoolNumber: String,
    val createdAt: LocalDateTime
)
