package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedDetailsResponse
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterReceivedDetailsService(
    private val letterFacade: LetterFacade,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): LetterReceivedDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterFacade.getReceivedLetterWithAccess(id, user.id)

        return LetterReceivedDetailsResponse(
            id = letter.id,
            content = letter.content,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            isDelivered = letter.isDelivered,
            isPassed = letter.isPassed,
            createdAt = letter.createdAt
        )
    }
}
