package com.example.falletterbackend.falletter.facade.question

import com.example.falletterbackend.falletter.entity.question.Question
import com.example.falletterbackend.falletter.entity.question.repository.QuestionRepository
import com.example.falletterbackend.falletter.exception.question.QuestionNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QuestionFacade(
    private val questionRepository: QuestionRepository
) {
    fun getQuestionById(id: Long): Question {
        return questionRepository.findByIdOrNull(id)
            ?: throw QuestionNotFoundException
    }
}
