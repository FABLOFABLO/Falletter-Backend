package com.example.falletterbackend.falletter.presentation.history

import com.example.falletterbackend.falletter.dto.history.request.BrickSaveHistoryRequest
import com.example.falletterbackend.falletter.dto.history.response.BrickUsedHistoryResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.history.BrickSaveHistoryService
import com.example.falletterbackend.falletter.service.history.BrickUsedHistoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "history", description = "벽돌 사용 기록 API")
@RestController
@RequestMapping("/history")
class HistoryController(
    private val brickUsedHistoryService: BrickUsedHistoryService,
    private val brickSaveHistoryService: BrickSaveHistoryService,
) {
    @Operation(summary = "벽돌 사용 기록 저장", description = "벽돌 사용 기록을 저장합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "기록 저장 성공")
    )
    @PostMapping(RestApiSpec.HISTORY_SAVE)
    @ResponseStatus(HttpStatus.CREATED)
    fun saveHistory(@RequestBody @Valid request: BrickSaveHistoryRequest) {
        brickSaveHistoryService.execute(request)
    }

    @Operation(summary = "벽돌 사용 기록 조회", description = "벽돌 사용 기록 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.HISTORY_USED)
    @ResponseStatus(HttpStatus.OK)
    fun usedHistory(): List<BrickUsedHistoryResponse> { return brickUsedHistoryService.execute() }
}
