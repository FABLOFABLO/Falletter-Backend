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
    fun findById(id: Long): Answer {
        return answerRepository.findByIdOrNull(id) ?: throw AnswerNotFoundException
    }

    fun findAllByTargetUserWithRelations(user: User): List<Answer> {
        return answerRepository.findAllByTargetUserIdWithRelations(user)
    }

    fun save(answer: Answer): Answer {
        return answerRepository.save(answer)
    }

    fun delete(answer: Answer) {
        answerRepository.delete(answer)
    }
}
