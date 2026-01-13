package com.example.falletterbackend.falletter.service.question

import com.example.falletterbackend.falletter.dto.question.response.QuestionResponse
import com.example.falletterbackend.falletter.facade.question.QuestionFacade
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestionAllGetService(
    private val questionFacade: QuestionFacade
) {
    @Cacheable(value = ["questions"], key = "'all'")
    @Transactional(readOnly = true)
    fun execute(): List<QuestionResponse> {
        return questionFacade.findAll()
            .map { QuestionResponse.from(it) }
    }
}
