package com.example.falletterbackend.falletter.service.letterBox

import com.example.falletterbackend.falletter.dto.letterBox.response.LetterReceivedDetailsResponse
import com.example.falletterbackend.falletter.entity.letterBox.repository.LetterBoxRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class LetterReceivedDetailsService(
    private val letterBoxRepository: LetterBoxRepository,
    private val userFacade: UserFacade
) {
    fun execute(id: Long): LetterReceivedDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterBoxRepository.findByIdAndReception_Id(id, user.id)
            ?: throw IllegalArgumentException("해당 레터를 찾을 수 없습니다.")

        return LetterReceivedDetailsResponse(
            id = letter.id,
            content = letter.content,
            isDelivered = letter.isDelivered,
            reception = letter.reception.id,
            createdAt = letter.createdAt
        )
    }
}