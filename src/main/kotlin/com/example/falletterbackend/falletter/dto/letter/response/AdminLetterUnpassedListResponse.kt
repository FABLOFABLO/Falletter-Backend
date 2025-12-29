package com.example.falletterbackend.falletter.dto.letter.response

import java.time.LocalDateTime

data class AdminLetterUnpassedListResponse(
    val id: Long,
    val isPassed: Boolean,
    val receptionId: Long,
    val senderId: Long,
    val createdAt: LocalDateTime
)
