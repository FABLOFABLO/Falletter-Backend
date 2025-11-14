package com.example.falletterbackend.falletter.dto.letter.response

import java.time.LocalDateTime

data class LetterReceivedDetailsResponse(
    val id: Long,
    val content:String,
    val isDelivered: Boolean,
    val isPassed: Boolean,
    val receptionId: Long,
    val createdAt: LocalDateTime
)
