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

        val letter = letterRepository.findByUser(user)
            ?: throw IllegalStateException("레터 정보가 존재하지 않습니다.")

        return LetterGetCountResponse(letter.letterCount)
    }
}