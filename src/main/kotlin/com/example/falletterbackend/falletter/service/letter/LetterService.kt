package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.common.config.gemini.GeminiConfig
import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedDetailsResponse
import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedListResponse
import com.example.falletterbackend.falletter.dto.letter.response.LetterSentDetailsResponse
import com.example.falletterbackend.falletter.dto.letter.response.LetterSentListResponse
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterService(
    private val letterFacade: LetterFacade,
    private val userFacade: UserFacade,
    private val itemFacade: ItemFacade,
    private val geminiConfig: GeminiConfig
) {
    @Transactional
    fun sendLetter(request: LetterSentRequest) {
        val user = userFacade.getCurrentUser()
        itemFacade.validateLetterAvailable(user)

        val reception = userFacade.getUserById(request.receptionId)
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

    @Transactional(readOnly = true)
    fun getReceivedList(): List<LetterReceivedListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterFacade.getReceivedPassedLettersWithSender(user.id)

        return letters.map { it.toReceivedListResponse() }
    }

    @Transactional(readOnly = true)
    fun getReceivedDetails(id: Long): LetterReceivedDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterFacade.getReceivedLetterWithAccess(id, user.id)

        return LetterReceivedDetailsResponse(
            id = letter.id,
            content = letter.content,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            isDelivered = letter.isDelivered,
            isPassed = letter.isPassed,
            createdAt = letter.createdAt
        )
    }

    @Transactional(readOnly = true)
    fun getSentList(): List<LetterSentListResponse> {
        val user = userFacade.getCurrentUser()
        val letters = letterFacade.getSentPassedLettersWithReception(user)

        return letters.map { it.toSentListResponse() }
    }

    @Transactional(readOnly = true)
    fun getSentDetails(id: Long): LetterSentDetailsResponse {
        val user = userFacade.getCurrentUser()
        val letter = letterFacade.getSentLetterWithAccess(id, user)

        return LetterSentDetailsResponse(
            id = letter.id,
            content = letter.content,
            receptionId = letter.reception.id,
            senderId = letter.sender.id,
            createdAt = letter.createdAt,
            isDelivered = letter.isDelivered,
            isPassed = letter.isPassed
        )
    }
}
