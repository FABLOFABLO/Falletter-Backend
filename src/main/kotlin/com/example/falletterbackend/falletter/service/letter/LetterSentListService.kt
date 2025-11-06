package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterSentListResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotSendException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LetterSentListService(
    private val userFacade: UserFacade,
    private val letterBoxRepository: LetterRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterSentListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterBoxRepository.findAllBySender(user)

        if (letters.isEmpty()) {
            throw LetterNotSendException
        }

        return letterBoxRepository.findAllBySender(user)
            .filter { it.sender.id == user.id }
            .map { letter ->
                LetterSentListResponse(
                    id = letter.id,
                    content = letter.content,
                    receptionId = letter.reception.id,
                    senderId = letter.sender.id,
                    isDelivered = letter.createdAt.plusHours(12).isBefore(LocalDateTime.now()),
                    createdAt = letter.createdAt
                )
            }
    }
}
