package com.example.falletterbackend.falletter.entity.terms.repository

import com.example.falletterbackend.falletter.entity.terms.Terms
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TermsRepository : JpaRepository<Terms, Long> {
    fun findByUser(user: User): Optional<Terms>
    fun findByUserId(userId: Long): Optional<Terms>
    fun existsByUser(user: User): Boolean
}
