package com.example.falletterbackend.falletter.entity.auth.repository

import com.example.falletterbackend.falletter.entity.auth.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
}