package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedDetailsResponse
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLetterUnpassedDetailsService(
    private val letterFacade: LetterFacade
) {
    @Transactional(readOnly = true)
    fun execute(letterId: Long): AdminLetterUnpassedDetailsResponse {
        val letter = letterFacade.getUnpassedLetter(letterId)

        return AdminLetterUnpassedDetailsResponse(
            id = letter.id,
            content = letter.content,
            isPassed = letter.isPassed,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            createdAt = letter.createdAt
        )
    }
}
