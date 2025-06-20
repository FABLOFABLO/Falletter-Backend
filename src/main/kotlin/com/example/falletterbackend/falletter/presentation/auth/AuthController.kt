package com.example.falletterbackend.falletter.presentation.auth

import com.example.falletterbackend.falletter.dto.auth.request.AuthMailMatchRequest
import com.example.falletterbackend.falletter.dto.auth.request.AuthMailVerifyRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.auth.AuthMailMatchService
import com.example.falletterbackend.falletter.service.auth.AuthMailVerifyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authMailVerifyService: AuthMailVerifyService,
    private val authMailMatchService: AuthMailMatchService
) {
    @PostMapping(RestApiSpec.MAIL_VERIFY)
    @ResponseStatus(HttpStatus.OK)
    fun authMailVerify(@RequestBody request: AuthMailVerifyRequest){
        authMailVerifyService.execute(request)
    }

    @PostMapping(RestApiSpec.MAIL_MATCH)
    @ResponseStatus(HttpStatus.OK)
    fun authMailMatch(@RequestBody request: AuthMailMatchRequest){
        authMailMatchService.execute(request)
    }
}