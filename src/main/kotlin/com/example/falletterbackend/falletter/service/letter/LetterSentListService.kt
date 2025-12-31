package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterSentListResponse
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSentListService(
    private val userFacade: UserFacade,
    private val letterFacade: LetterFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterSentListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterFacade.getSentLetters(user)

        return letters
            .filter { it.isPassed }
            .map { it.toSentListResponse() }
    }
}
