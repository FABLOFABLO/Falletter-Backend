package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterSentListResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotSendException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSentListService(
    private val userFacade: UserFacade,
    private val letterRepository: LetterRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterSentListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterRepository.findAllBySender(user)

        if (letters.isEmpty()) {
            throw LetterNotSendException
        }

        return letters
            .filter { it.isPassed }
            .map { it.toSentListResponse() }
    }
}
