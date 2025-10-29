package com.example.falletterbackend.falletter.dto.letter.request

import com.example.falletterbackend.falletter.entity.user.User


data class LetterSentRequest(
    val content: String,
    val reception: User,
)
