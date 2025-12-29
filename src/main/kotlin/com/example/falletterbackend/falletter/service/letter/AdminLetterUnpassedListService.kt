package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedListResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLetterUnpassedListService(
    private val letterRepository: LetterRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<AdminLetterUnpassedListResponse> {
        val letters = letterRepository.findAllByIsPassed(false)

        if (letters.isEmpty()) {
            throw LetterNotFoundException
        }

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
