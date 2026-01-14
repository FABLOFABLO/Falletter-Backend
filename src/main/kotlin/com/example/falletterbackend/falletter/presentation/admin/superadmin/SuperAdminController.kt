package com.example.falletterbackend.falletter.presentation.admin.superadmin

import com.example.falletterbackend.falletter.dto.admin.superadmin.response.AdminListResponse
import com.example.falletterbackend.falletter.dto.admin.superadmin.response.AdminRequestListResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.admin.superadmin.SuperAdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Super Admin", description = "슈퍼 어드민 API")
@RestController
@RequestMapping("/super-admin")
class SuperAdminController(
    private val superAdminService: SuperAdminService
) {
    @Operation(summary = "가입 신청 목록 조회", description = "어드민 가입 신청 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.SUPER_ADMIN_REQUESTS)
    @ResponseStatus(HttpStatus.OK)
    fun getAdminRequests(): List<AdminRequestListResponse> {
        return superAdminService.getAdminRequests()
    }

    @Operation(summary = "가입 승인", description = "어드민 가입 신청을 승인합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "승인 성공"),
        ApiResponse(responseCode = "404", description = "어드민을 찾을 수 없음"),
        ApiResponse(responseCode = "409", description = "이미 처리된 신청")
    )
    @PatchMapping(RestApiSpec.SUPER_ADMIN_APPROVE)
    @ResponseStatus(HttpStatus.OK)
    fun approveAdmin(@PathVariable("admin-id") adminId: Long) {
        superAdminService.approveAdmin(adminId)
    }

    @Operation(summary = "가입 반려", description = "어드민 가입 신청을 반려합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "반려 성공"),
        ApiResponse(responseCode = "404", description = "어드민을 찾을 수 없음"),
        ApiResponse(responseCode = "409", description = "이미 처리된 신청")
    )
    @PatchMapping(RestApiSpec.SUPER_ADMIN_REJECT)
    @ResponseStatus(HttpStatus.OK)
    fun rejectAdmin(@PathVariable("admin-id") adminId: Long) {
        superAdminService.rejectAdmin(adminId)
    }

    @Operation(summary = "승인된 관리자 목록 조회", description = "승인된 관리자 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.SUPER_ADMIN_ADMINS)
    @ResponseStatus(HttpStatus.OK)
    fun getApprovedAdmins(): List<AdminListResponse> {
        return superAdminService.getApprovedAdmins()
    }
}
