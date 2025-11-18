package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserSaveRequest
import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.entity.question.repository.QuestionRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.question.QuestionNotFoundException
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class AnswerUserSaveService(
    private val userFacade: UserFacade,
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository
) {
    fun execute(request: AnswerUserSaveRequest) {
        val user = userFacade.getCurrentUser()

        val question = questionRepository.findById(request.questionId)
            .orElseThrow { QuestionNotFoundException }

        val targetUser = userRepository.findById(request.targetUser)
            .orElseThrow { UserNotFoundException }


        answerRepository.save(
            Answer(
                question = question,
                targetUserId = targetUser,
                writerId = user
            )
        )
    }
}
