package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.comment.Comment
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "댓글 정보")
data class CommunityListCommentResponse(
    @Schema(description = "댓글 ID", example = "1")
    val commentId: Long,

    @Schema(description = "작성자 정보")
    val user: CommunityListUserResponse,

    @Schema(description = "댓글 내용", example = "좋은 글이네요!")
    val comment: String,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime,

    @Schema(description = "수정일시", example = "2024-12-30T10:00:00")
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(comment: Comment): CommunityListCommentResponse {
            return CommunityListCommentResponse(
                commentId = comment.id,
                user = CommunityListUserResponse.from(comment.user),
                comment = comment.comment,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt
            )
        }
    }
}
