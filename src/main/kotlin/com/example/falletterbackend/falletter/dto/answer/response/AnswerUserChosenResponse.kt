package com.example.falletterbackend.falletter.dto.answer.response

import com.example.falletterbackend.falletter.entity.user.enums.Gender
import java.time.LocalDateTime

data class AnswerUserChosenResponse(
    val id: Long,
    val schoolNumber: String,
    val name: String,
    val gender: Gender,
    val question: String,
    val emoji: String,
    val questionId: Long,
    val targetUserId: Long,
    val writerUserId: Long,
    val createdAt: LocalDateTime
)
