package com.example.falletterbackend.falletter.dto.letter.request

data class LetterSentRequest(
    val content: String,
    val reception: Long,
)
