package com.example.falletterbackend.falletter.presentation.admin.auth

import com.example.falletterbackend.falletter.dto.admin.auth.request.AdminSignInRequest
import com.example.falletterbackend.falletter.dto.admin.auth.request.AdminSignUpRequest
import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.admin.auth.AdminAuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Auth", description = "어드민 인증 API")
@RestController
@RequestMapping("/admin/auth")
class AdminAuthController(
    private val adminAuthService: AdminAuthService
) {
    @Operation(summary = "어드민 가입 신청", description = "새로운 어드민 가입을 신청합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "가입 신청 성공"),
        ApiResponse(responseCode = "409", description = "이미 존재하는 이메일")
    )
    @PostMapping(RestApiSpec.ADMIN_SIGN_UP)
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid request: AdminSignUpRequest) {
        adminAuthService.signUp(request)
    }

    @Operation(summary = "어드민 로그인", description = "이메일과 비밀번호로 로그인합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "로그인 성공"),
        ApiResponse(responseCode = "400", description = "비밀번호 불일치"),
        ApiResponse(responseCode = "403", description = "승인되지 않은 어드민"),
        ApiResponse(responseCode = "404", description = "어드민을 찾을 수 없음")
    )
    @PostMapping(RestApiSpec.ADMIN_SIGN_IN)
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody @Valid request: AdminSignInRequest): AuthTokenResponse {
        return adminAuthService.signIn(request)
    }
}
