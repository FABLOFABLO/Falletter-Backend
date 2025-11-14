package com.example.falletterbackend.falletter.entity.auth.repository

import com.example.falletterbackend.falletter.entity.auth.EmailVerify
import org.springframework.data.repository.CrudRepository

interface EmailVerifyRepository : CrudRepository<EmailVerify, String>{
    fun existsByEmail(email: String): Boolean
}
