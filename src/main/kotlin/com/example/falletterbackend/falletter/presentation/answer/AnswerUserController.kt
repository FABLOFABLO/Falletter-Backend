package com.example.falletterbackend.falletter.presentation.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserRequest
import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserHistoryResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.answer.AnswerUserHistoryService
import com.example.falletterbackend.falletter.service.answer.AnswerUserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/answer")
class AnswerUserController(
    private val answerUserService: AnswerUserService,
    private val answerUserHistoryService: AnswerUserHistoryService
) {
    @PostMapping(RestApiSpec.ANSWER_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun answerPost(@RequestBody @Valid request: AnswerUserRequest) {
        answerUserService.execute(request)
    }

    @GetMapping(RestApiSpec.ANSWER_USED)
    @ResponseStatus(HttpStatus.OK)
    fun answerUsed(): List<AnswerUserHistoryResponse> {
        return answerUserHistoryService.execute()
    }
}