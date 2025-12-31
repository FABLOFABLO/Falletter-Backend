package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterSentDetailsResponse
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSentDetailsService(
    private val letterFacade: LetterFacade,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): LetterSentDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterFacade.getSentLetterWithAccess(id, user)

        return LetterSentDetailsResponse(
            id = letter.id,
            content = letter.content,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            createdAt = letter.createdAt,
            isDelivered = letter.isDelivered,
            isPassed = letter.isPassed
        )
    }
}
