package com.example.falletterbackend.falletter.presentation.auth

import com.example.falletterbackend.falletter.dto.auth.request.AuthMailMatchRequest
import com.example.falletterbackend.falletter.dto.auth.request.AuthMailVerifyRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.auth.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "auth", description = "인증 API")
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @Operation(summary = "이메일 인증코드 발송", description = "입력한 이메일로 인증코드를 발송합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "인증코드 발송 성공")
    )
    @PostMapping(RestApiSpec.MAIL_VERIFY)
    @ResponseStatus(HttpStatus.OK)
    fun authMailVerify(@RequestBody request: AuthMailVerifyRequest) { authService.sendVerificationEmail(request) }

    @Operation(summary = "이메일 인증코드 확인", description = "발송된 인증코드와 입력한 코드가 일치하는지 확인합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "인증코드 일치"),
        ApiResponse(responseCode = "403", description = "인증코드 불일치 또는 존재하지 않음")
    )
    @PostMapping(RestApiSpec.MAIL_MATCH)
    @ResponseStatus(HttpStatus.OK)
    fun authMailMatch(@RequestBody request: AuthMailMatchRequest) { authService.verifyCode(request) }
}
