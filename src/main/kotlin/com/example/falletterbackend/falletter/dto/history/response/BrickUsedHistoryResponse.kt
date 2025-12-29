package com.example.falletterbackend.falletter.dto.history.response

import com.example.falletterbackend.falletter.entity.history.HistoryType
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import java.time.LocalDateTime

data class BrickUsedHistoryResponse(
    val id: Long,
    val description: String?,
    val amount: Long,
    val type: HistoryType,
    val question: String,
    val targetUserId: Long,
    val writerUserId: Long,
    val gender: Gender,
    val schoolNumber: String,
    val createdAt: LocalDateTime
)
