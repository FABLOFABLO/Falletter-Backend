package com.example.falletterbackend.falletter.presentation.letterBox

import com.example.falletterbackend.falletter.dto.letterBox.request.LetterSentRequest
import com.example.falletterbackend.falletter.dto.letterBox.response.LetterSentDetailsResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letterBox.LetterSendByUserService
import com.example.falletterbackend.falletter.service.letterBox.LetterSentDetailsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/letterBox")
class LetterBoxController(
    private val letterSendByUserService: LetterSendByUserService,
    private val letterSentDetailsService: LetterSentDetailsService
) {
    @PostMapping(RestApiSpec.LETTER_BOX_SENT)
    @ResponseStatus(HttpStatus.CREATED)
    fun sendLetter(@RequestBody @Valid request: LetterSentRequest) {
        letterSendByUserService.execute(request)
    }

    @GetMapping(RestApiSpec.LETTER_BOX_SENT_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun sentDetail(@PathVariable("letter-id") id: Long): LetterSentDetailsResponse {
        return letterSentDetailsService.execute(id)
    }
}