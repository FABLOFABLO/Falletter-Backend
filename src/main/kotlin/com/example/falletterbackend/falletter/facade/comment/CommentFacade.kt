package com.example.falletterbackend.falletter.facade.comment

import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.entity.comment.repository.CommentRepository
import com.example.falletterbackend.falletter.exception.comment.CommentNotFoundException
import com.example.falletterbackend.falletter.exception.user.UserMismatchException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentFacade(
    private val commentRepository: CommentRepository
) {
    fun getCommentById(id: Long): Comment {
        return commentRepository.findByIdOrNull(id)
            ?: throw CommentNotFoundException
    }

    fun getCommentByIdWithOwnerCheck(id: Long, userId: Long): Comment {
        val comment = getCommentById(id)
        if (comment.user.id != userId) {
            throw UserMismatchException
        }
        return comment
    }

    fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    fun delete(comment: Comment) {
        commentRepository.delete(comment)
    }
}
