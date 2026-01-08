package com.example.falletterbackend.falletter.entity.hint.repository

import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface HintRepository : JpaRepository<Hint, Long> {
    fun findByIdAndUser(id: Long, user: User): HintResponse
}
