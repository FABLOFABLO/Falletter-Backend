package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedListResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AdminLetterUnpassedListService(
    private val letterRepository: LetterRepository,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterReceivedListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterRepository.findAllByReception_IdAndIsPassed(user.id, false)

        if (letters.isEmpty()) {
            throw LetterNotFoundException
        }

        return letters.map {
            LetterReceivedListResponse(
                id = it.id,
                content = it.content,
                isDelivered = it.createdAt.plusHours(12).isBefore(LocalDateTime.now()),
                isPassed = it.isPassed,
                receptionId = it.reception.id,
                senderId = it.sender.id,
                createdAt = it.createdAt
            )
        }
    }
}
