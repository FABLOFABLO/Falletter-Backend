package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.user.User
import java.time.LocalDateTime

data class CommunityPostsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: PostListUserResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(community: Community): CommunityPostsResponse {
            return CommunityPostsResponse(
                id = community.id,
                title = community.title,
                content = community.content,
                author = PostListUserResponse(community.author.name),
                createdAt = community.createdAt,
                updatedAt = community.updatedAt
            )
        }
    }
}

data class PostListUserResponse(
    val name: String
) {
    companion object {
        fun format(user: User): PostListUserResponse {
            return PostListUserResponse(
                name = user.name
            )
        }
    }
}


//data class FeedListCommentResponse(
//    val user: FeedListUserResponse,
//    val comment: String
//) {
//    companion object {
//        fun format(comment: Comment): FeedListCommentResponse {
//            return FeedListCommentResponse(
//                user = FeedListUserResponse.format(comment.user),
//                comment = comment.comment
//            )
//        }
//    }
//}