package com.example.falletterbackend.falletter.dto.letterBox.response

import java.time.LocalDateTime

data class LetterReceivedDetailsResponse(
    val content:String,
    val isDelivered: Boolean,
    val reception: Long,
    val createdAt: LocalDateTime
)
