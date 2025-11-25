package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.user.User
import java.time.LocalDateTime

data class CommunityPostsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: CommunityListUserResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
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

data class CommunityListUserResponse(
    val userId: Long,
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

data class CommunityListCommentResponse(
    val commentId: Long,
    val user: CommunityListUserResponse,
    val comment: String,
    val createdAt: LocalDateTime,
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
