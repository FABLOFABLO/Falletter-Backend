package com.example.falletterbackend.falletter.dto.notice.response

import java.time.LocalDateTime

data class NoticeDetailsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val authorName: String,
    val createdAt: LocalDateTime
)
