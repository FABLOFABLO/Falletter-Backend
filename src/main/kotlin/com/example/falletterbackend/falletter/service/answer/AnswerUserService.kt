package com.example.falletterbackend.falletter.service.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserRequest
import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.entity.question.repository.QuestionRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class AnswerUserService(
    private val userFacade: UserFacade,
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository
) {
    fun execute(request: AnswerUserRequest) {
        val user = userFacade.getCurrentUser()

        val question = questionRepository.findById(request.questionId)
            .orElseThrow { IllegalArgumentException("질문을 찾을 수 없습니다.") }

        val targetUser = userRepository.findById(request.targetUser)
            .orElseThrow { IllegalArgumentException("대상 유저를 찾을 수 없습니다.") }


        answerRepository.save(
            Answer(
                question = question,
                targetUserId = targetUser,
                writerId = user
            )
        )
    }
}