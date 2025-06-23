package com.example.falletterbackend.falletter.service.letterBox

import com.example.falletterbackend.falletter.dto.letterBox.response.LetterSentDetailsResponse
import com.example.falletterbackend.falletter.entity.letterBox.LetterBox
import com.example.falletterbackend.falletter.entity.letterBox.repository.LetterBoxRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSentDetailsService(
    private val letterBoxRepository: LetterBoxRepository,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): LetterSentDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter: LetterBox = letterBoxRepository.findByIdAndSender(id, user)
            ?: throw IllegalArgumentException("해당 편지를 찾을 수 없습니다.")

        if (letter.sender.id != user.id) {
            throw IllegalAccessException("해당 편지에 접근할 권한이 없습니다.")
        }

        return LetterSentDetailsResponse(
            id = letter.id,
            content = letter.content,
            reception = letter.reception.id,
            sender = letter.sender.id,
            createdAt = letter.createdAt
        )

    }
}