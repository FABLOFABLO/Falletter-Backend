package com.example.falletterbackend.falletter.presentation.admin.letter

import com.example.falletterbackend.falletter.dto.admin.letter.response.AdminLetterUnpassedDetailsResponse
import com.example.falletterbackend.falletter.dto.admin.letter.response.AdminLetterUnpassedListResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.AdminLetterUnpassedDetailsService
import com.example.falletterbackend.falletter.service.letter.AdminLetterUnpassedListService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Letter", description = "관리자 편지 관리 API")
@RestController
@RequestMapping("/admin")
class AdminUnpassedController(
    private val adminLetterUnpassedListService: AdminLetterUnpassedListService,
    private val adminLetterUnpassedDetailsService: AdminLetterUnpassedDetailsService,
) {
    @Operation(summary = "미승인 편지 목록 조회", description = "승인되지 않은 편지 목록을 조회합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음")
    )
    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedAll(): List<AdminLetterUnpassedListResponse> {
        return adminLetterUnpassedListService.execute()
    }

    @Operation(summary = "미승인 편지 상세 조회", description = "미승인 편지의 상세 내용을 조회합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음"),
        ApiResponse(responseCode = "404", description = "편지를 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedDetail(
        @Parameter(description = "편지 ID", example = "1")
        @PathVariable("letter-id") id: Long
    ): AdminLetterUnpassedDetailsResponse {
        return adminLetterUnpassedDetailsService.execute(id)
    }
}
