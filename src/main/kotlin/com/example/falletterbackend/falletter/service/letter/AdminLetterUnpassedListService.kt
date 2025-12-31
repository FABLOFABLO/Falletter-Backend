package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedListResponse
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLetterUnpassedListService(
    private val letterFacade: LetterFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<AdminLetterUnpassedListResponse> {
        val letters = letterFacade.getUnpassedLetters()

        return letters.map {
            AdminLetterUnpassedListResponse(
                id = it.id,
                isPassed = it.isPassed,
                receptionId = it.reception.id,
                senderId = it.sender.id,
                createdAt = it.createdAt
            )
        }
    }
}
