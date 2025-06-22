package com.example.falletterbackend.falletter.dto.letterBox.response

import java.time.LocalDateTime

data class LetterSentListResponse(
    val id: Long,
    val content: String,
    val receptionId: Long,
    val senderId: Long,
    val isDelivered: Boolean,
    val createdAt: LocalDateTime
)