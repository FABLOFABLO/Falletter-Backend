package com.example.falletterbackend.falletter.dto.letterBox.response

import java.time.LocalDateTime

data class LetterReceivedListResponse(
    val id: Long,
    val content: String,
    val isDelivered: Boolean,
    val reception: Long,
    val sender: Long,
    val createdAt: LocalDateTime
)