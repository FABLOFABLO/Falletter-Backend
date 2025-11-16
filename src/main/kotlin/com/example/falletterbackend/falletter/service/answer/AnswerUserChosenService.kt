package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserChosenResponse
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerUserChosenService(
    private val userFacade: UserFacade,
    private val answerRepository: AnswerRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): List<AnswerUserChosenResponse> {
        val user = userFacade.getCurrentUser()
        val answers = answerRepository.findAllByTargetUserId(user)

        return answers.map {
            AnswerUserChosenResponse(
                id = it.id,
                questionId = it.question.id,
                question = it.question.question,
                emoji = it.question.emoji,
                targetUserId = it.targetUserId.id,
                writerUserId = it.writerId.id,
                gender = it.targetUserId.gender,
                schoolNumber = it.targetUserId.schoolNumber,
                name = it.writerId.name,
                createdAt = it.createdAt
            )
        }
    }
}
