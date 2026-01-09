package com.example.falletterbackend.falletter.presentation.suspend

import com.example.falletterbackend.falletter.dto.user.response.UserSuspendHistoryResponse
import com.example.falletterbackend.falletter.dto.user.response.UserSuspendReasonResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.suspend.SuspendService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "suspend", description = "경고/정지 내역 API")
@RestController
@RequestMapping("/suspend")
class SuspendController(
    private val suspendService: SuspendService
) {
    @Operation(summary = "내 경고/정지 내역 조회", description = "자신의 경고 및 정지 내역을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.SUSPEND_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun getSuspendHistory(): List<UserSuspendHistoryResponse> {
        return suspendService.getSuspendHistory()
    }

    @Operation(summary = "정지 사유 조회", description = "특정 정지의 사유를 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "정지 내역을 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.SUSPEND_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun getSuspendReason(@PathVariable("suspend-id") id: Long): UserSuspendReasonResponse {
        return suspendService.getSuspendReason(id)
    }
}
