package com.example.falletterbackend.falletter.service.question

import com.example.falletterbackend.falletter.dto.question.response.QuestionResponse
import com.example.falletterbackend.falletter.entity.question.repository.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestionAllGetService(
    private val questionRepository: QuestionRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<QuestionResponse> {
        return questionRepository.findAll()
            .map { QuestionResponse.from(it) }
    }
}
