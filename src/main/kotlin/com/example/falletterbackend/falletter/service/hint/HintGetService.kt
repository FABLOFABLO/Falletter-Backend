package com.example.falletterbackend.falletter.service.hint

import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.hint.repository.HintRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class HintGetService(
    private val userFacade: UserFacade,
    private val hintRepository: HintRepository
) {
    fun execute(id: Long): HintResponse {
        val user = userFacade.getCurrentUser()

        return hintRepository.findByIdAndUser(id, user)
    }
}