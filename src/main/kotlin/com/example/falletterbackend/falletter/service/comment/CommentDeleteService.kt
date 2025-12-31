package com.example.falletterbackend.falletter.service.comment

import com.example.falletterbackend.falletter.entity.comment.repository.CommentRepository
import com.example.falletterbackend.falletter.facade.comment.CommentFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentDeleteService(
    private val userFacade: UserFacade,
    private val commentFacade: CommentFacade,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()
        commentFacade.getCommentByIdWithOwnerCheck(id, user.id)

        commentRepository.deleteById(id)
    }
}
