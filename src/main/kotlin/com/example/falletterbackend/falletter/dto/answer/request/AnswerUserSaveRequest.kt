package com.example.falletterbackend.falletter.dto.answer.request

data class AnswerUserSaveRequest(
    val questionId: Long,
    val targetUser: Long
)
