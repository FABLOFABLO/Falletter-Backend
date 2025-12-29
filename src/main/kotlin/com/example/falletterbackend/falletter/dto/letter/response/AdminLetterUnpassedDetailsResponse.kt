package com.example.falletterbackend.falletter.dto.letter.response

import java.time.LocalDateTime

data class AdminLetterUnpassedDetailsResponse(
    val id: Long,
    val content: String,
    val isPassed: Boolean,
    val receptionId: Long,
    val senderId: Long,
    val createdAt: LocalDateTime
)
