package com.example.falletterbackend.falletter.presentation.brick

import com.example.falletterbackend.falletter.dto.brick.response.BrickTimerResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.timer.BrickTimerGetRemainingService
import com.example.falletterbackend.falletter.service.timer.BrickTimerStartService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Timer", description = "브릭 타이머 API")
@RestController
@RequestMapping("/timer")
class BrickTimerController(
    private val brickTimerStartService: BrickTimerStartService,
    private val brickTimerGetRemainingService: BrickTimerGetRemainingService
) {
    @Operation(summary = "브릭 타이머 시작", description = "5시간 타이머를 시작합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "타이머 시작 성공")
    )
    @PostMapping(RestApiSpec.TIMER_BRICK_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun startTimer() {
        brickTimerStartService.execute()
    }

    @Operation(summary = "브릭 타이머 조회", description = "남은 시간(초)을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.TIMER_BRICK_GET)
    @ResponseStatus(HttpStatus.OK)
    fun getRemainingTime(): BrickTimerResponse {
        return brickTimerGetRemainingService.execute()
    }
}
