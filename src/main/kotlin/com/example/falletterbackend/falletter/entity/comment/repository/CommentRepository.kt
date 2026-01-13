package com.example.falletterbackend.falletter.entity.comment.repository

import com.example.falletterbackend.falletter.entity.comment.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
