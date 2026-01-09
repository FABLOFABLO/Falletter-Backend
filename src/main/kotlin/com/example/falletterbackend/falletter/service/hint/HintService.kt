package com.example.falletterbackend.falletter.service.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintSaveRequest
import com.example.falletterbackend.falletter.dto.hint.request.HintUpdateRequest
import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.answer.repository.AnswerRepository
import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.entity.hint.repository.HintRepository
import com.example.falletterbackend.falletter.exception.answer.AnswerNotFoundException
import com.example.falletterbackend.falletter.exception.hint.HintNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HintService(
    private val userFacade: UserFacade,
    private val answerRepository: AnswerRepository,
    private val hintRepository: HintRepository
) {
    @Transactional
    fun saveHint(request: HintSaveRequest) {
        val user = userFacade.getCurrentUser()
        val answer = answerRepository.findByIdOrNull(request.answerId)
            ?: throw AnswerNotFoundException

        hintRepository.save(
            Hint(
                firstHint = request.firstHint,
                secondHint = request.secondHint,
                thirdHint = request.thirdHint,
                user = user,
                answer = answer,
            )
        )
    }

    fun getHintDetail(answerId: Long): HintResponse {
        val user = userFacade.getCurrentUser()
        return hintRepository.findByIdAndUser(answerId, user)
    }

    @Transactional
    fun updateHint(request: HintUpdateRequest) {
        val hint = hintRepository.findByIdOrNull(request.hintId)
            ?: throw HintNotFoundException
        hint.update(request.firstHint, request.secondHint, request.thirdHint)
    }
}
