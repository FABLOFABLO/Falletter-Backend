package com.example.falletterbackend.falletter.presentation.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.dto.letter.response.LetterGetCountResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.LetterGetCountService
import com.example.falletterbackend.falletter.service.letter.LetterSendByUserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/letter")
class LetterController(
    private val letterSendByUserService: LetterSendByUserService,
    private val letterGetCountService: LetterGetCountService
) {
    @PostMapping(RestApiSpec.LETTER_SENT)
    @ResponseStatus(HttpStatus.CREATED)
    fun sendLetter(request: LetterSentRequest) {
        letterSendByUserService.execute(request)
    }

    @GetMapping(RestApiSpec.LETTER_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountLetter(): LetterGetCountResponse {
        return letterGetCountService.execute()
    }
}