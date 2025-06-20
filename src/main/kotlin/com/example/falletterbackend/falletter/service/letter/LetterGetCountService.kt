package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.response.LetterGetCountResponse
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterGetCountService(
    private val userFacade: UserFacade,
    private val letterRepository: LetterRepository
) {
    @Transactional(readOnly = true)
    fun execute(): LetterGetCountResponse{
        val user = userFacade.getCurrentUser()

        return letterRepository.findByUser(user)
    }
}