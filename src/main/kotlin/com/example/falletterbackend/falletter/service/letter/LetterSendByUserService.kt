package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSendByUserService(
    private val itemRepository: ItemRepository,
    private val letterRepository: LetterRepository,
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute(request: LetterSentRequest) {
        val user = userFacade.getCurrentUser()

        if (!itemRepository.existsByUserAndLetterCountGreaterThan(user, 0)) {
            throw LetterNotFoundException
        }

        val reception = userRepository.findById(request.reception)
            .orElseThrow { UserNotFoundException }


        val sendLetter = Letter(
            content = request.content,
            reception = reception,
            sender = user
        )
        letterRepository.save(sendLetter)
    }
}
