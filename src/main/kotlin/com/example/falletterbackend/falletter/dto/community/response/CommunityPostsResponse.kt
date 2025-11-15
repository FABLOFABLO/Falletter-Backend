package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.user.User
import java.time.LocalDateTime

data class CommunityPostsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: PostListUserResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val comment: List<PostListCommentResponse>
) {
    companion object {
        fun from(community: Community): CommunityPostsResponse {
            return CommunityPostsResponse(
                id = community.id,
                title = community.title,
                content = community.content,
                author = PostListUserResponse(community.author.id, community.author.name),
                createdAt = community.createdAt,
                updatedAt = community.updatedAt,
                comment = community.comments.map { PostListCommentResponse.format(it) }
            )
        }
    }
}

data class PostListUserResponse(
    val userId: Long,
    val name: String
) {
    companion object {
        fun format(user: User): PostListUserResponse {
            return PostListUserResponse(
                userId = user.id,
                name = user.name
            )
        }
    }
}

data class PostListCommentResponse(
    val commentId: Long,
    val user: PostListUserResponse,
    val comment: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun format(comment: Comment): PostListCommentResponse {
            return PostListCommentResponse(
                commentId = comment.id,
                user = PostListUserResponse.format(comment.user),
                comment = comment.comment,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt
            )
        }
    }
}
