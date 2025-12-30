package com.example.falletterbackend.falletter.dto.notice.response

import java.time.LocalDateTime

data class NoticeListResponse(
    val id: Long,
    val title: String,
    val authorName: String,
    val createdAt: LocalDateTime
)
