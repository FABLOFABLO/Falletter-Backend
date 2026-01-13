package com.example.falletterbackend.falletter.facade.auth

import com.example.falletterbackend.falletter.entity.auth.repository.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun deleteRefreshTokenByEmail(email: String) {
        refreshTokenRepository.deleteById(email)
    }
}
