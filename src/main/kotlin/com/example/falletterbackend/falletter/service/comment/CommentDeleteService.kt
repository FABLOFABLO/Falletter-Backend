package com.example.falletterbackend.falletter.service.comment

import com.example.falletterbackend.falletter.entity.comment.repository.CommentRepository
import com.example.falletterbackend.falletter.exception.user.UserMismatchException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentDeleteService(
    private val userFacade: UserFacade,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun execute(id: Long){
        val user = userFacade.getCurrentUser()
        val comment = commentRepository.findById(id).orElseThrow()

        if(user.id != comment.user.id) throw UserMismatchException

        commentRepository.deleteById(id)
    }
}