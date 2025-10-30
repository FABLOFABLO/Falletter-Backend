package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedDetailsResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class LetterReceivedDetailsService(
    private val letterBoxRepository: LetterRepository,
    private val userFacade: UserFacade
) {
    fun execute(id: Long): LetterReceivedDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterBoxRepository.findByIdAndReception_Id(id, user.id)
            ?: throw LetterNotFoundException

        return LetterReceivedDetailsResponse(
            id = letter.id,
            content = letter.content,
            isDelivered = letter.isDelivered,
            reception = letter.reception.id,
            createdAt = letter.createdAt
        )
    }
}
