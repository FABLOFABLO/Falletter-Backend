package com.example.falletterbackend.falletter.presentation.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserSaveRequest
import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserChosenResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.answer.AnswerUserChosenService
import com.example.falletterbackend.falletter.service.answer.AnswerUserSaveService
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
    private val answerUserService: AnswerUserSaveService,
    private val answerUserChosenService: AnswerUserChosenService
) {
    @PostMapping(RestApiSpec.ANSWER_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun answerPost(@RequestBody @Valid request: AnswerUserSaveRequest) {
        answerUserService.execute(request)
    }

    @GetMapping(RestApiSpec.ANSWER_PICK)
    @ResponseStatus(HttpStatus.OK)
    fun answerPicked(): List<AnswerUserChosenResponse> {
        return answerUserChosenService.execute()
    }
}
