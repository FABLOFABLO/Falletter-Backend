package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSendByUserService(
    private val itemRepository: ItemRepository,
    private val letterRepository: LetterRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: LetterSentRequest) {
        val user = userFacade.getCurrentUser()

        if (!itemRepository.existsByUserAndLetterCountGreaterThan(user, 0)) {
            throw IllegalStateException("보유한 래터가 없습니다.")
        }

        val sendLetter = Letter(
            content = request.content,
            reception = request.reception,
            sender = user
        )
        letterRepository.save(sendLetter)
    }
}