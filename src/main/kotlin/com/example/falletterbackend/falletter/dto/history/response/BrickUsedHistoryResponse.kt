package com.example.falletterbackend.falletter.dto.history.response

import com.example.falletterbackend.falletter.entity.user.Gender
import java.time.LocalDateTime

data class BrickUsedHistoryResponse(
    val id: Long,
    val question: String,
    val targetUserId: Long,
    val writerUserId: Long,
    val gender: Gender,
    val schoolNumber: String,
    val createdAt: LocalDateTime
)
