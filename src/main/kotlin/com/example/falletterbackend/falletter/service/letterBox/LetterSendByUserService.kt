package com.example.falletterbackend.falletter.service.letterBox

import com.example.falletterbackend.falletter.dto.letterBox.request.LetterSentRequest
import com.example.falletterbackend.falletter.entity.letterBox.LetterBox
import com.example.falletterbackend.falletter.entity.letterBox.repository.LetterBoxRepository
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSendByUserService(
    private val letterRepository: LetterRepository,
    private val letterBoxRepository: LetterBoxRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: LetterSentRequest) {
        val user = userFacade.getCurrentUser()

        val reception = userFacade.getUserById(request.reception)
            ?: throw IllegalArgumentException("해당 수신 사용자가 존재하지 않습니다.")

        val userLetter = letterRepository.findFirstByUserOrderByIdAsc(user)
            ?: throw IllegalStateException("보낼 수 있는 래터가 없습니다.")

        if (userLetter.letterCount <= 0) {
            throw IllegalStateException("래터 수가 부족합니다.")
        }

        userLetter.letterCount -= 1
        letterRepository.save(userLetter)

        val sendLetter = LetterBox(
            content = request.content,
            reception = reception,
            sender = user

        )
        letterBoxRepository.save(sendLetter)
    }
}