package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.common.config.gemini.GeminiConfig
import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterSendByUserService(
    private val letterFacade: LetterFacade,
    private val userFacade: UserFacade,
    private val itemFacade: ItemFacade,
    private val geminiConfig: GeminiConfig
) {
    @Transactional
    fun execute(request: LetterSentRequest) {
        val user = userFacade.getCurrentUser()
        itemFacade.validateLetterAvailable(user)

        val reception = userFacade.getUserById(request.reception)
        val isSafe = geminiConfig.checkForProfanity(request.content)

        val sendLetter = Letter(
            content = request.content,
            isDelivered = false,
            isPassed = isSafe,
            reception = reception,
            sender = user
        )

        letterFacade.saveLetter(sendLetter)
    }
}
