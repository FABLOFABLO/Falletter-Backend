package com.example.falletterbackend.falletter.dto.community.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "게시글 목록 응답")
data class CommunityPostsListResponse(
    @Schema(description = "게시글 ID", example = "1")
    val id: Long,

    @Schema(description = "게시글 제목", example = "오늘의 공지사항")
    val title: String,

    @Schema(description = "게시글 내용", example = "오늘 점심은 짜장면입니다.")
    val content: String,

    @Schema(description = "작성자 정보")
    val author: CommunityListUserResponse,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime,

    @Schema(description = "수정일시", example = "2024-12-30T10:00:00")
    val updatedAt: LocalDateTime
)
