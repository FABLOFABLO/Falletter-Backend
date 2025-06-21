package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserGetAllStudentService(
    private val userRepository: UserRepository
) {
    fun execute(): List<UserGetAllStudentResponse> {
        return userRepository.findAllBy()
    }
}
