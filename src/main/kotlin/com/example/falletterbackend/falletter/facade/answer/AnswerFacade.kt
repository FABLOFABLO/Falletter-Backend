package com.example.falletterbackend.falletter.facade.answer

import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import org.springframework.stereotype.Component


@Component
class AnswerFacade(
    private val answerRepository: AnswerRepository
) {
    fun getCurrentAnswer(id: Long): Answer {
        return answerRepository.findById(id).orElseThrow {
            com.example.falletterbackend.falletter.exception.answer.AnswerNotFoundException
        }
    }
}