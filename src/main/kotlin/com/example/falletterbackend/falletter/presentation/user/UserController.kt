package com.example.falletterbackend.falletter.presentation.user

import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.dto.user.request.UserSignInRequest
import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.dto.user.response.UserInfoResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "user", description = "사용자 API")
@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "회원가입 성공"),
        ApiResponse(responseCode = "409", description = "이미 존재하는 이메일")
    )
    @PostMapping(RestApiSpec.USER_SIGN_UP)
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid request: UserSignUpRequest) { userService.signUp(request) }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "로그인 성공"),
        ApiResponse(responseCode = "400", description = "비밀번호 불일치"),
        ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    )
    @PostMapping(RestApiSpec.USER_SIGN_IN)
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody @Valid request: UserSignInRequest): AuthTokenResponse {
        return userService.signIn(request)
    }

    @Operation(summary = "로그아웃", description = "현재 사용자를 로그아웃합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "로그아웃 성공")
    )
    @DeleteMapping(RestApiSpec.USER_LOG_OUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun logout() { userService.logout() }

    @Operation(summary = "내 정보 조회", description = "현재 로그인한 사용자의 정보를 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.USER_USERS)
    @ResponseStatus(HttpStatus.OK)
    fun userInfo(): UserInfoResponse { return userService.getInfo() }

    @Operation(summary = "전체 학생 목록 조회", description = "전체 학생 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.USER_ALL_STUDENT)
    @ResponseStatus(HttpStatus.OK)
    fun getAllStudent(): List<UserGetAllStudentResponse> {
        return userService.getAllStudents()
    }
}
