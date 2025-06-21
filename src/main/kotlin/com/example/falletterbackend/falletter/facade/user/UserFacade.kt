package com.example.falletterbackend.falletter.facade.user

import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val email: String = SecurityContextHolder.getContext().authentication.name
        return getByAccountId(email)
    }

    fun checkAccountIdExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun getUserById(userId: Long): User? {
        return userRepository.findByIdOrNull(userId)
    }

    fun getByAccountId(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException
    }
}