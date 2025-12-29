package com.example.falletterbackend.falletter.service.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintRequest
import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.entity.hint.repository.HintRepository
import com.example.falletterbackend.falletter.facade.answer.AnswerFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class HintSaveService(
    private val userFacade: UserFacade,
    private val answerFacade: AnswerFacade,
    private val hintRepository: HintRepository
) {
    @Transactional
    fun execute(request: HintRequest) {
        val user = userFacade.getCurrentUser()
        val answer = answerFacade.getCurrentAnswer(request.answer )

        hintRepository.save(
            Hint(
                firstHint = request.first,
                secondHint = request.second,
                thirdHint = request.third,
                user = user,
                answer = answer,
            )
        )
    }
}