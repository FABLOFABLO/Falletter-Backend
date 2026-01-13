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
    fun findById(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException
    }

    fun findAll(): List<Question> {
        return questionRepository.findAll()
    }

    fun save(question: Question): Question {
        return questionRepository.save(question)
    }
}
