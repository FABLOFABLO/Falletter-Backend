package com.example.falletterbackend.falletter.entity.user.repository

import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun findBySchoolNumber(schoolNumber: String): User?

    fun existsBySchoolNumber(schoolNumber: String): Boolean
}