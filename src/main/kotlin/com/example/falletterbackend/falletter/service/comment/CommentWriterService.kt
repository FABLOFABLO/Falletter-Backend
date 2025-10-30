package com.example.falletterbackend.falletter.service.comment

import com.example.falletterbackend.falletter.dto.comment.request.CommentRequest
import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.entity.comment.repository.CommentRepository
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentWriterService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun execute(id: Long, request: CommentRequest) {
        val user = userFacade.getCurrentUser()
        val community = communityFacade.getCurrentCommunity(id)

        commentRepository.save(
            Comment(
                comment = request.comment,
                community = community,
                user = user
            )
        )
    }
}
