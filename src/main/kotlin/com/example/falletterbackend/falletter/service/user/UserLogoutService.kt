package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.entity.auth.repository.RefreshTokenRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class UserLogoutService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userFacade: UserFacade
) {
    fun execute() {
        val currentUser = userFacade.getCurrentUser()
        refreshTokenRepository.deleteById(currentUser.email)
    }
}
