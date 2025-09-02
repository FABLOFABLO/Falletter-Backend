package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.entity.letterBox.LetterBox
import com.example.falletterbackend.falletter.entity.letterBox.repository.LetterBoxRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSendByUserService(
    private val letterBoxRepository: LetterBoxRepository,
    private val letterRepository: LetterRepository
) {
    @Transactional
    fun execute(request: LetterSentRequest) {
        val itemLetter = letterRepository.findByUser(request.sender)
            ?: throw IllegalStateException("보낼 수 있는 래터가 없습니다.")

        if (itemLetter.letterCount <= 0) {
            throw IllegalStateException("래터 수가 부족합니다.")
        }

        val sendLetter = LetterBox(
            content = request.content,
            reception = request.reception,
            sender = request.sender
        )
        letterBoxRepository.save(sendLetter)

        itemLetter.changeLetterCount(-1)
        letterRepository.save(itemLetter)
    }
}