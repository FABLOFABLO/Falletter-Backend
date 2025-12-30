package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.user.User
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "게시글 상세 응답")
data class CommunityPostsResponse(
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
    val updatedAt: LocalDateTime,

    @Schema(description = "댓글 목록")
    val comment: List<CommunityListCommentResponse>
) {
    companion object {
        fun from(community: Community): CommunityPostsResponse {
            return CommunityPostsResponse(
                id = community.id,
                title = community.title,
                content = community.content,
                author = CommunityListUserResponse(community.author.id, community.author.name),
                createdAt = community.createdAt,
                updatedAt = community.updatedAt,
                comment = community.comments.map { CommunityListCommentResponse.format(it) }
            )
        }
    }
}

@Schema(description = "사용자 정보")
data class CommunityListUserResponse(
    @Schema(description = "사용자 ID", example = "1")
    val userId: Long,

    @Schema(description = "이름", example = "홍길동")
    val name: String
) {
    companion object {
        fun format(user: User): CommunityListUserResponse {
            return CommunityListUserResponse(
                userId = user.id,
                name = user.name
            )
        }
    }
}

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
        fun format(comment: Comment): CommunityListCommentResponse {
            return CommunityListCommentResponse(
                commentId = comment.id,
                user = CommunityListUserResponse.format(comment.user),
                comment = comment.comment,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt
            )
        }
    }
}
