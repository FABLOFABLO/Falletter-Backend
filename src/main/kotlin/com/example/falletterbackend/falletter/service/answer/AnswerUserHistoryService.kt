package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserHistoryResponse
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerUserHistoryService(
    private val answerRepository: AnswerRepository,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<AnswerUserHistoryResponse>{
        val user = userFacade.getCurrentUser()

        val answers = answerRepository.findAllByWriterId(user)

        return answers.map {
            AnswerUserHistoryResponse(
                id = it.id,
                questionId = it.question.id,
                targetUserId = it.targetUserId.id,
                writerId = it.writerId.id,
                gender = it.targetUserId.gender,
                schoolNumber = it.targetUserId.schoolNumber,
                createdAt = it.createdAt
            )
        }

    }
}
