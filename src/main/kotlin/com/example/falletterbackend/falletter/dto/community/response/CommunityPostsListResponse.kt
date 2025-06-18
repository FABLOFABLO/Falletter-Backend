package com.example.falletterbackend.falletter.dto.community.response

import java.time.LocalDateTime

data class CommunityPostsListResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: PostListUserResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)