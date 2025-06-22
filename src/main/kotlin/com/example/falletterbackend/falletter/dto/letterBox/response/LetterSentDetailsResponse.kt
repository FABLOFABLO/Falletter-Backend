package com.example.falletterbackend.falletter.dto.letterBox.response

import java.time.LocalDateTime

data class LetterSentDetailsResponse(
    val content: String,
    val reception: Long,
    val sender: Long,
    val createdAt: LocalDateTime
)