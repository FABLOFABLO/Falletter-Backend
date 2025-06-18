package com.example.falletterbackend.falletter.presentation.question

import com.example.falletterbackend.falletter.dto.question.response.QuestionResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.question.QuestionAllGetService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/question")
class QuestionController(
    private val questionAllGetService: QuestionAllGetService
) {
    @GetMapping(RestApiSpec.QUESTION_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun question(): List<QuestionResponse> { return questionAllGetService.execute() }
}