package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedDetailsResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLetterUnpassedDetailsService(
    private val letterRepository: LetterRepository
) {
    @Transactional(readOnly = true)
    fun execute(letterId: Long): AdminLetterUnpassedDetailsResponse {
        val letter = letterRepository.findByIdAndIsPassed(letterId, false)
            ?: throw LetterNotFoundException

        return AdminLetterUnpassedDetailsResponse(
            id = letter.id,
            content = letter.content,
            isPassed = letter.isPassed,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            createdAt = letter.createdAt
        )
    }
}
