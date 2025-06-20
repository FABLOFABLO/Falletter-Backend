package com.example.falletterbackend.falletter.presentation.letterBox

import com.example.falletterbackend.falletter.dto.letterBox.request.LetterSentRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letterBox.LetterSendByUserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/letterBox")
class LetterBoxController(
    private val letterSendByUserService: LetterSendByUserService
) {
    @PostMapping(RestApiSpec.LETTER_SENT)
    @ResponseStatus(HttpStatus.CREATED)
    fun sendLetter(request: LetterSentRequest) {
        letterSendByUserService.execute(request)
    }
}