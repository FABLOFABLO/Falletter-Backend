package com.example.falletterbackend.falletter.presentation.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.dto.letter.response.*
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.LetterSendByUserService
import com.example.falletterbackend.falletter.service.letter.*
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
@RequestMapping("/letter")
class LetterController(
    private val letterSendByUserService: LetterSendByUserService,
    private val letterSentDetailsService: LetterSentDetailsService,
    private val letterSentListService: LetterSentListService,
    private val letterReceivedDetailsService: LetterReceivedDetailsService,
    private val letterReceivedListService: LetterReceivedListService,
    private val adminLetterUnpassedListService: AdminLetterUnpassedListService,
    private val adminLetterUnpassedDetailsService: AdminLetterUnpassedDetailsService
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

    @GetMapping(RestApiSpec.LETTER_BOX_SENT_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun sentAll(): List<LetterSentListResponse>{ return letterSentListService.execute() }

    @GetMapping(RestApiSpec.LETTER_BOX_RECEIVED_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun receivedDetail(@PathVariable("letter-id") id: Long): LetterReceivedDetailsResponse {
        return letterReceivedDetailsService.execute(id)
    }

    @GetMapping(RestApiSpec.LETTER_BOX_RECEIVED_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun receivedAll(): List<LetterReceivedListResponse> {
        return letterReceivedListService.execute()
    }

    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedAll(): List<AdminLetterUnpassedListResponse> {
        return adminLetterUnpassedListService.execute()
    }

    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedDetail(@PathVariable("letter-id") id: Long): AdminLetterUnpassedDetailsResponse {
        return adminLetterUnpassedDetailsService.execute(id)
    }
}
