package com.example.falletterbackend.falletter.dto.answer.response

import com.example.falletterbackend.falletter.entity.user.Gender
import java.time.LocalDateTime

data class AnswerUserChosenResponse(
    val id: Long,
    val schoolNumber: String,
    val gender: Gender,
    val question: Long,
    val targetUser: Long,
    val writerUser: Long,
    val createdAt: LocalDateTime
)
