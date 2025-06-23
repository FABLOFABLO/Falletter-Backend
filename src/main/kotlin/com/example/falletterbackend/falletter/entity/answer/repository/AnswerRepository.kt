package com.example.falletterbackend.falletter.entity.answer.repository

import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findAllByWriterId(writer: User): List<Answer>

    fun findAllByTargetUserId(target: User): List<Answer>
}