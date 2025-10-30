package com.example.falletterbackend.falletter.dto.letter.response

import java.time.LocalDateTime

data class LetterSentDetailsResponse(
    val id: Long,
    val content: String,
    val reception: Long,
    val sender: Long,
    val createdAt: LocalDateTime
)
