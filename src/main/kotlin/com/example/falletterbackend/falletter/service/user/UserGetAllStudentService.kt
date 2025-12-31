package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class UserGetAllStudentService(
    private val userFacade: UserFacade
) {
    fun execute(): List<UserGetAllStudentResponse> {
        return userFacade.getAllStudents()
    }
}

