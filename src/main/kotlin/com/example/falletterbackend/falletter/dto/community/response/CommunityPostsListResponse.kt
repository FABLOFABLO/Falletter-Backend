package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.community.Community
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

    @Schema(description = "삭제 여부", example = "false")
    val isDeleted: Boolean,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime,

    @Schema(description = "수정일시", example = "2024-12-30T10:00:00")
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(entity: Community) = CommunityPostsListResponse(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            author = CommunityListUserResponse.from(entity.author),
            isDeleted = entity.isDeleted,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
}
