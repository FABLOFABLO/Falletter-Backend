package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedListResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.exception.letter.LetterNotReceivedException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LetterReceivedListService(
    private val letterBoxRepository: LetterRepository,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<LetterReceivedListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterBoxRepository.findAllByReception_Id(user.id)

        if (letters.isEmpty()) {
            throw LetterNotReceivedException
        }

        return letterBoxRepository.findAllByReception_Id(user.id)
            .filter { it.reception.id == user.id }
            .map {
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
