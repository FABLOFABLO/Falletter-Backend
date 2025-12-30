package com.example.falletterbackend.falletter.presentation.question

import com.example.falletterbackend.falletter.dto.question.response.QuestionResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.question.QuestionAllGetService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Question", description = "질문 API")
@RestController
@RequestMapping("/question")
class QuestionController(
    private val questionAllGetService: QuestionAllGetService
) {
    @Operation(summary = "질문 전체 조회", description = "모든 질문 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.QUESTION_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun question(): List<QuestionResponse> { return questionAllGetService.execute() }
}
