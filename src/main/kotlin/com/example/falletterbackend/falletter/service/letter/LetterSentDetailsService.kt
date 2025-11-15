package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterSentDetailsResponse
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNoAccessPermission
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LetterSentDetailsService(
    private val letterBoxRepository: LetterRepository,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): LetterSentDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterBoxRepository.findByIdAndSender(id, user)
            ?: throw LetterNoAccessPermission

        return LetterSentDetailsResponse(
            id = letter.id,
            content = letter.content,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            createdAt = letter.createdAt,
            isDelivered = letter.createdAt.plusHours(12).isBefore(LocalDateTime.now()),
            isPassed = letter.isPassed
        )
    }
}
