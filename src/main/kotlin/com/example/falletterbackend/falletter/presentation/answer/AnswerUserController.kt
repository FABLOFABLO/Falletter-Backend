package com.example.falletterbackend.falletter.presentation.answer

import com.example.falletterbackend.falletter.dto.answer.request.AnswerUserSaveRequest
import com.example.falletterbackend.falletter.dto.answer.response.AnswerUserChosenResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.answer.AnswerUserChosenService
import com.example.falletterbackend.falletter.service.answer.AnswerUserSaveService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Answer", description = "답변 API")
@RestController
@RequestMapping("/answer")
class AnswerUserController(
    private val answerUserService: AnswerUserSaveService,
    private val answerUserChosenService: AnswerUserChosenService
) {
    @Operation(summary = "답변 저장", description = "사용자의 답변을 저장합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "답변 저장 성공")
    )
    @PostMapping(RestApiSpec.ANSWER_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun answerPost(@RequestBody @Valid request: AnswerUserSaveRequest) { answerUserService.execute(request) }

    @Operation(summary = "선택한 답변 조회", description = "사용자가 선택한 답변 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.ANSWER_PICK)
    @ResponseStatus(HttpStatus.OK)
    fun answerPicked(): List<AnswerUserChosenResponse> { return answerUserChosenService.execute() }
}
