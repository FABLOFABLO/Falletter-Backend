package com.example.falletterbackend.falletter.service.hint

import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.facade.hint.HintFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class HintGetDetailService(
    private val userFacade: UserFacade,
    private val hintFacade: HintFacade
) {
    fun execute(id: Long): HintResponse {
        val user = userFacade.getCurrentUser()

        return hintFacade.getHintByIdAndUser(id, user)
    }
}