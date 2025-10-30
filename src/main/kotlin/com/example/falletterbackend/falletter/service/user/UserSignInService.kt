package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.common.security.TokenProvider
import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.dto.user.request.UserSignInRequest
import com.example.falletterbackend.falletter.exception.user.IncorrectPasswordException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignInService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {
    @Transactional
    fun execute(request: UserSignInRequest): AuthTokenResponse {
        val user = userFacade.getByAccountId(request.email)
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw IncorrectPasswordException
        }
        return tokenProvider.generateToken(request.email)
    }
}
