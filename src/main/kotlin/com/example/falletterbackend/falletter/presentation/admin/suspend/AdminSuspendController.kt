package com.example.falletterbackend.falletter.presentation.admin.suspend

import com.example.falletterbackend.falletter.dto.admin.user.request.AdminUserSuspendRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.admin.suspend.AdminSuspendService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Suspend", description = "관리자 경고/정지 관리 API")
@RestController
@RequestMapping("/admin")
class AdminSuspendController(
    private val adminSuspendService: AdminSuspendService
) {
    @Operation(summary = "경고 부여", description = "학생에게 경고를 부여합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "경고 부여 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음"),
        ApiResponse(responseCode = "404", description = "학생을 찾을 수 없음")
    )
    @PostMapping(RestApiSpec.ADMIN_WARNING_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun giveWarning(
        @Parameter(description = "학생 ID", example = "1")
        @PathVariable("user-id") id: Long
    ) {
        adminSuspendService.createWarning(id)
    }

    @Operation(summary = "정지 부여", description = "학생에게 정지를 부여합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "정지 부여 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음"),
        ApiResponse(responseCode = "404", description = "학생을 찾을 수 없음")
    )
    @PostMapping(RestApiSpec.ADMIN_BLOCK_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun giveBlock(
        @Parameter(description = "학생 ID", example = "1")
        @PathVariable("user-id") id: Long,
        @RequestBody request: AdminUserSuspendRequest
    ) {
        adminSuspendService.createBlock(id, request)
    }
}
