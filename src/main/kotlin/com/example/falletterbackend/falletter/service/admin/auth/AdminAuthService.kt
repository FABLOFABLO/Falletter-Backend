package com.example.falletterbackend.falletter.service.admin.auth

import com.example.falletterbackend.common.security.TokenProvider
import com.example.falletterbackend.falletter.dto.admin.auth.request.AdminSignInRequest
import com.example.falletterbackend.falletter.dto.admin.auth.request.AdminSignUpRequest
import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.entity.admin.Admin
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.example.falletterbackend.falletter.exception.admin.AdminNotApprovedException
import com.example.falletterbackend.falletter.exception.user.IncorrectPasswordException
import com.example.falletterbackend.falletter.facade.admin.AdminFacade
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminAuthService(
    private val adminFacade: AdminFacade,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {
    @Transactional
    fun signUp(request: AdminSignUpRequest) {
        adminFacade.validateEmailNotExists(request.email)

        val admin = Admin(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            gender = request.gender
        )

        adminFacade.save(admin)
    }

    @Transactional
    fun signIn(request: AdminSignInRequest): AuthTokenResponse {
        val admin = adminFacade.getByEmail(request.email)

        if (!passwordEncoder.matches(request.password, admin.password)) {
            throw IncorrectPasswordException
        }

        if (admin.status != AdminStatus.APPROVED) {
            throw AdminNotApprovedException
        }

        return tokenProvider.generateAdminToken(request.email)
    }
}
