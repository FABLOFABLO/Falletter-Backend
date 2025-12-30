package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedListResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.exception.letter.LetterNotReceivedException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

        return letters
            .filter { it.isPassed == true }
            .map {
                LetterReceivedListResponse(
                    id = it.id,
                    content = it.content,
                    receptionId = it.reception.id,
                    senderId = it.sender.id,
                    isDelivered = it.isDelivered,
                    isPassed = it.isPassed,
                    createdAt = it.createdAt
                )
            }
    }
}
