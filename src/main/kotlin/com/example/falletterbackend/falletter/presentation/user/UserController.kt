package com.example.falletterbackend.falletter.presentation.user

import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.dto.user.request.UserSignInRequest
import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.user.UserLogoutService
import com.example.falletterbackend.falletter.service.user.UserSignInService
import com.example.falletterbackend.falletter.service.user.UserSignUpService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val userLogoutService: UserLogoutService
) {
    @PostMapping(RestApiSpec.USER_SIGN_UP)
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid request: UserSignUpRequest) {
        userSignUpService.execute(request)
    }

    @PostMapping(RestApiSpec.USER_SIGN_IN)
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody @Valid request: UserSignInRequest): AuthTokenResponse {
        return userSignInService.execute(request)
    }

    @DeleteMapping(RestApiSpec.USER_LOG_OUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun logout() { userLogoutService.execute() }

}