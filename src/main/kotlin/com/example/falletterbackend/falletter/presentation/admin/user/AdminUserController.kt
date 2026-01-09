package com.example.falletterbackend.falletter.presentation.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserListResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserProfileResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.admin.user.AdminUserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin User", description = "관리자 학생 관리 API")
@RestController
@RequestMapping("/admin")
class AdminUserController(
    private val adminUserService: AdminUserService
) {
    @Operation(summary = "학생 전체 목록 조회", description = "전체 학생 목록을 페이징하여 조회합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음")
    )
    @GetMapping(RestApiSpec.ADMIN_USER_LIST)
    @ResponseStatus(HttpStatus.OK)
    fun getUserList(
        @PageableDefault(size = 20) pageable: Pageable
    ): Page<AdminUserListResponse> {
        return adminUserService.getUserList(pageable)
    }

    @Operation(summary = "학생 프로필 조회", description = "학생 상세 정보 및 제재 내역을 조회합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음"),
        ApiResponse(responseCode = "404", description = "학생을 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.ADMIN_USER_PROFILE)
    @ResponseStatus(HttpStatus.OK)
    fun getUserProfile(
        @Parameter(description = "학생 ID", example = "1")
        @PathVariable("user-id") id: Long
    ): AdminUserProfileResponse {
        return adminUserService.getUserProfile(id)
    }
}
