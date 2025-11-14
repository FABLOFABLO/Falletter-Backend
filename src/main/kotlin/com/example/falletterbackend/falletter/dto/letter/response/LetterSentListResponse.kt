package com.example.falletterbackend.falletter.dto.letter.response

import java.time.LocalDateTime

data class LetterSentListResponse(
    val id: Long,
    val content: String,
    val receptionId: Long,
    val senderId: Long,
    val isDelivered: Boolean,
    val isPassed: Boolean,
    val createdAt: LocalDateTime
)
