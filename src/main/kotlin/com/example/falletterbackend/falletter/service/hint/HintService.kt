package com.example.falletterbackend.falletter.service.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintSaveRequest
import com.example.falletterbackend.falletter.dto.hint.request.HintUpdateRequest
import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.facade.answer.AnswerFacade
import com.example.falletterbackend.falletter.facade.hint.HintFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HintService(
    private val userFacade: UserFacade,
    private val answerFacade: AnswerFacade,
    private val hintFacade: HintFacade
) {
    @Transactional
    fun saveHint(request: HintSaveRequest) {
        val user = userFacade.getCurrentUser()
        val answer = answerFacade.findById(request.answerId)

        hintFacade.save(
            Hint(
                firstHint = request.firstHint,
                secondHint = request.secondHint,
                thirdHint = request.thirdHint,
                user = user,
                answer = answer,
            )
        )
    }

    @Transactional(readOnly = true)
    fun getHintDetail(answerId: Long): HintResponse {
        val user = userFacade.getCurrentUser()
        return hintFacade.findByIdAndUser(answerId, user)
    }

    @Transactional
    fun updateHint(request: HintUpdateRequest) {
        val hint = hintFacade.findById(request.hintId)
        hint.update(request.firstHint, request.secondHint, request.thirdHint)
    }
}
