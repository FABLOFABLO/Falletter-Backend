package com.example.falletterbackend.falletter.dto.letterBox.request

import com.example.falletterbackend.falletter.entity.user.User

data class LetterSentRequest(
    val content: String,
    val reception: User,
    val sender: User
)
