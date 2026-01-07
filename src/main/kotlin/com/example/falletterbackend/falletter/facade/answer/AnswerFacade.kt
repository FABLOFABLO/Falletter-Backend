package com.example.falletterbackend.falletter.facade.answer

import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.exception.answer.AnswerNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AnswerFacade(
    private val answerRepository: AnswerRepository
) {
    fun getCurrentAnswer(id: Long): Answer {
        return answerRepository.findByIdOrNull(id) ?: throw AnswerNotFoundException
    }

    fun save(answer: Answer): Answer {
        return answerRepository.save(answer)
    }

    fun getAnswersByTargetUserWithRelations(target: User): List<Answer> {
        return answerRepository.findAllByTargetUserIdWithRelations(target)
    }
}