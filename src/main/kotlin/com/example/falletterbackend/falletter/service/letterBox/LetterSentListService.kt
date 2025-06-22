package com.example.falletterbackend.falletter.service.letterBox

import com.example.falletterbackend.falletter.dto.letterBox.response.LetterSentListResponse
import com.example.falletterbackend.falletter.entity.letterBox.repository.LetterBoxRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSentListService(
    private val userFacade: UserFacade,
    private val letterBoxRepository: LetterBoxRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterSentListResponse> {
        val currentUser = userFacade.getCurrentUser()

        val letters = letterBoxRepository.findAllBySender(currentUser)

        return letters.map { letter ->
            LetterSentListResponse(
                id = letter.id,
                content = letter.content,
                receptionId = letter.reception.id,
                senderId = letter.sender.id,
                isDelivered = letter.isDelivered,
                createdAt = letter.createdAt
            )
        }
    }
}