package com.example.falletterbackend.falletter.entity.user.repository

import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.entity.user.User

interface UserRepositoryCustom {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun existsBySchoolNumber(schoolNumber: String): Boolean

    fun findAllStudents(): List<UserGetAllStudentResponse>
}
