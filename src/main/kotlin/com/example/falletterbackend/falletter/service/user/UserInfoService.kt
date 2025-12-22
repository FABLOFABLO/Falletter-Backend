package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserInfoResponse
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val userRepository: UserRepository,
) {
    fun execute(): UserInfoResponse {
        val authentication = SecurityContextHolder.getContext().authentication
        val email = authentication.name

        val user = userRepository.findByEmail(email)
            ?: throw UserNotFoundException

        return UserInfoResponse(
            email = user.email,
            schoolNumber = user.schoolNumber,
            name = user.name,
            gender = user.gender,
            theme = user.theme,
            profileImage = user.profileImage,
            id = user.id
        )
    }
}
