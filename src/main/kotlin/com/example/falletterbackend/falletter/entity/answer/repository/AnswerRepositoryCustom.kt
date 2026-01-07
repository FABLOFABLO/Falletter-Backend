package com.example.falletterbackend.falletter.entity.answer.repository

import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.user.User

interface AnswerRepositoryCustom {
    fun findAllByTargetUserIdWithRelations(target: User): List<Answer>
}
