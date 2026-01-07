package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserSaveRequest
import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.facade.answer.AnswerFacade
import com.example.falletterbackend.falletter.facade.question.QuestionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class AnswerUserSaveService(
    private val userFacade: UserFacade,
    private val questionFacade: QuestionFacade,
    private val answerFacade: AnswerFacade
) {
    fun execute(request: AnswerUserSaveRequest) {
        val user = userFacade.getCurrentUser()
        val question = questionFacade.getQuestionById(request.questionId)
        val targetUser = userFacade.getUserById(request.targetUser)

        answerFacade.save(
            Answer(
                question = question,
                targetUserId = targetUser,
                writerId = user
            )
        )
    }
}
