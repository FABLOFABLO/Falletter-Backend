package com.example.falletterbackend.falletter.entity.auth.repository

import com.example.falletterbackend.falletter.entity.auth.EmailVerify
import org.springframework.data.jpa.repository.JpaRepository

interface EmailVerifyRepository : JpaRepository<EmailVerify, String>{
    fun existsByEmail(email: String): Boolean
}
