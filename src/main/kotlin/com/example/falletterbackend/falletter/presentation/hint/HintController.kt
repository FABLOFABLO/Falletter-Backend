package com.example.falletterbackend.falletter.presentation.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintSaveRequest
import com.example.falletterbackend.falletter.dto.hint.request.HintUpdateRequest
import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.hint.HintService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "hint", description = "힌트 API")
@RestController
@RequestMapping("/hint")
class HintController(
    private val hintService: HintService
) {
    @Operation(summary = "힌트 저장", description = "새로운 힌트를 저장합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "힌트 저장 성공")
    )
    @PostMapping(RestApiSpec.HINT_SAVE)
    @ResponseStatus(HttpStatus.CREATED)
    fun saveHint(@RequestBody request: HintSaveRequest) {
        hintService.saveHint(request)
    }

    @Operation(summary = "힌트 조회", description = "답변 ID로 힌트를 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "힌트를 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.HINT_GET_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun getHintDetail(
        @Parameter(description = "답변 ID", example = "1")
        @PathVariable("answer-id") id: Long
    ): HintResponse {
        return hintService.getHintDetail(id)
    }

    @Operation(summary = "힌트 수정", description = "힌트를 수정합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "수정 성공"),
        ApiResponse(responseCode = "404", description = "힌트를 찾을 수 없음")
    )
    @PatchMapping(RestApiSpec.HINT_UPDATE)
    @ResponseStatus(HttpStatus.OK)
    fun updateHint(@RequestBody request: HintUpdateRequest) {
        hintService.updateHint(request)
    }
}
