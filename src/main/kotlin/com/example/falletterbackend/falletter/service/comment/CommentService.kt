package com.example.falletterbackend.falletter.service.comment

import com.example.falletterbackend.falletter.dto.comment.request.CommentRequest
import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.facade.comment.CommentFacade
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade,
    private val commentFacade: CommentFacade
) {
    @Transactional
    fun writeComment(postId: Long, request: CommentRequest) {
        val user = userFacade.getCurrentUser()
        val community = communityFacade.getCurrentCommunity(postId)

        commentFacade.save(
            Comment(
                comment = request.comment,
                community = community,
                user = user
            )
        )
    }

    @Transactional
    fun deleteComment(commentId: Long) {
        val user = userFacade.getCurrentUser()
        val comment = commentFacade.getCommentByIdWithOwnerCheck(commentId, user.id)

        commentFacade.delete(comment)
    }
}
