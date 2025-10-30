package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterSentDetailsResponse
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSentDetailsService(
    private val letterBoxRepository: LetterRepository,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): LetterSentDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter: Letter = letterBoxRepository.findByIdAndSender(id, user)
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
