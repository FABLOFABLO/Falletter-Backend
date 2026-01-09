package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserSaveRequest
import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserChosenResponse
import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.entity.question.repository.QuestionRepository
import com.example.falletterbackend.falletter.exception.question.QuestionNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val userFacade: UserFacade,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository
) {
    fun saveAnswer(request: AnswerUserSaveRequest) {
        val user = userFacade.getCurrentUser()
        val question = questionRepository.findByIdOrNull(request.questionId)
            ?: throw QuestionNotFoundException
        val targetUser = userFacade.getUserById(request.targetUser)

        answerRepository.save(
            Answer(
                question = question,
                targetUserId = targetUser,
                writerId = user
            )
        )
    }

    @Transactional(readOnly = true)
    fun getChosenAnswers(): List<AnswerUserChosenResponse> {
        val user = userFacade.getCurrentUser()
        val answers = answerRepository.findAllByTargetUserIdWithRelations(user)

        return answers.map {
            AnswerUserChosenResponse(
                id = it.id,
                questionId = it.question.id,
                question = it.question.question,
                emoji = it.question.emoji,
                targetUserId = it.targetUserId.id,
                writerUserId = it.writerId.id,
                gender = it.writerId.gender,
                schoolNumber = it.writerId.schoolNumber,
                name = it.writerId.name,
                createdAt = it.createdAt
            )
        }
    }
}
