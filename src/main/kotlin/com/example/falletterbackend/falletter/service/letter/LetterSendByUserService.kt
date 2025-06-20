package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.LetterBox
import com.example.falletterbackend.falletter.entity.letter.repository.LetterBoxRepository
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSendByUserService(
    private val letterRepository: LetterRepository,
    private val letterBoxRepository: LetterBoxRepository
) {
    @Transactional
    fun execute(request: LetterSentRequest) {
        val userLetter = letterRepository.findFirstByUserOrderByIdAsc(request.sender)
            ?: throw IllegalStateException("보낼 수 있는 래터가 없습니다.")

        if (userLetter.letterCount <= 0) {
            throw IllegalStateException("래터 수가 부족합니다.")
        }

        userLetter.letterCount -= 1
        letterRepository.save(userLetter)

        val sendLetter = LetterBox(
            content = request.content,
            reception = request.reception,
            sender = request.sender
        )
        letterBoxRepository.save(sendLetter)
    }
}