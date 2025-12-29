package com.example.falletterbackend.falletter.service.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintUpdateRequest
import com.example.falletterbackend.falletter.facade.hint.HintFacade
import org.springframework.stereotype.Service

@Service
class HintUpdateService(
    private val hintFacade: HintFacade
) {
    fun execute(request: HintUpdateRequest) {
        val hint = hintFacade.getCurrentHint(request.hintId)

        hint.update(request.firstHint, request.secondHint, request.thirdHint)
    }
}