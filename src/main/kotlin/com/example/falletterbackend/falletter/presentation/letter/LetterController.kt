package com.example.falletterbackend.falletter.presentation.letter

import com.example.falletterbackend.falletter.dto.letter.request.LetterSentRequest
import com.example.falletterbackend.falletter.dto.letter.response.*
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.LetterSendByUserService
import com.example.falletterbackend.falletter.service.letter.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "letter", description = "편지 API")
@RestController
@RequestMapping("/letter")
class LetterController(
    private val letterSendByUserService: LetterSendByUserService,
    private val letterSentDetailsService: LetterSentDetailsService,
    private val letterSentListService: LetterSentListService,
    private val letterReceivedDetailsService: LetterReceivedDetailsService,
    private val letterReceivedListService: LetterReceivedListService,
) {
    @Operation(summary = "편지 발송", description = "새로운 편지를 발송합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "편지 발송 성공"),
        ApiResponse(responseCode = "400", description = "편지 수 부족"),
        ApiResponse(responseCode = "404", description = "수신자를 찾을 수 없음")
    )
    @PostMapping(RestApiSpec.LETTER_BOX_SENT)
    @ResponseStatus(HttpStatus.CREATED)
    fun sendLetter(@RequestBody @Valid request: LetterSentRequest) {
        letterSendByUserService.execute(request)
    }

    @Operation(summary = "보낸 편지 상세 조회", description = "보낸 편지의 상세 내용을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "편지를 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.LETTER_BOX_SENT_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun sentDetail(
        @Parameter(description = "편지 ID", example = "1")
        @PathVariable("letter-id") id: Long
    ): LetterSentDetailsResponse {
        return letterSentDetailsService.execute(id)
    }

    @Operation(summary = "보낸 편지 목록 조회", description = "보낸 편지 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.LETTER_BOX_SENT_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun sentAll(): List<LetterSentListResponse> { return letterSentListService.execute() }

    @Operation(summary = "받은 편지 상세 조회", description = "받은 편지의 상세 내용을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "편지를 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.LETTER_BOX_RECEIVED_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun receivedDetail(
        @Parameter(description = "편지 ID", example = "1")
        @PathVariable("letter-id") id: Long
    ): LetterReceivedDetailsResponse {
        return letterReceivedDetailsService.execute(id)
    }

    @Operation(summary = "받은 편지 목록 조회", description = "받은 편지 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.LETTER_BOX_RECEIVED_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun receivedAll(): List<LetterReceivedListResponse> {
        return letterReceivedListService.execute()
    }
}
