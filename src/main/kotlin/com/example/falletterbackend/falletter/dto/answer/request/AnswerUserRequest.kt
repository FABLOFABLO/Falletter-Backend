package com.example.falletterbackend.falletter.dto.answer.request

data class AnswerUserRequest(
    val questionId: Long,
    val targetUser: Long
)
