package com.example.falletterbackend.falletter.facade

import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val accountId: String = SecurityContextHolder.getContext().authentication.name
        return getByAccountId(accountId)
    }

    fun checkAccountIdExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun getByAccountId(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException
    }
}