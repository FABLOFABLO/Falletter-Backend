package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserSaveRequest
import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserChosenResponse
import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.facade.answer.AnswerFacade
import com.example.falletterbackend.falletter.facade.question.QuestionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val userFacade: UserFacade,
    private val questionFacade: QuestionFacade,
    private val answerFacade: AnswerFacade
) {
    @Transactional
    fun saveAnswer(request: AnswerUserSaveRequest) {
        val user = userFacade.getCurrentUser()
        val question = questionFacade.findById(request.questionId)
        val targetUser = userFacade.getUserById(request.targetUser)

        answerFacade.save(
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
        val answers = answerFacade.findAllByTargetUserWithRelations(user)

        return answers.map { AnswerUserChosenResponse.from(it) }
    }
}
