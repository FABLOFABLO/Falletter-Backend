package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedListResponse
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterReceivedListService(
    private val letterFacade: LetterFacade,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterReceivedListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterFacade.getReceivedLetters(user.id)

        return letters
            .filter { it.isPassed }
            .map { it.toReceivedListResponse() }
    }
}
