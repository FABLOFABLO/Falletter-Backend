package com.example.falletterbackend.falletter.entity.sanction.repository

import com.example.falletterbackend.falletter.entity.sanction.Sanction
import com.example.falletterbackend.falletter.entity.sanction.enums.SanctionType
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface SanctionRepository : JpaRepository<Sanction, Long> {
    fun findAllByUser(user: User): List<Sanction>
    fun findByUserAndEndDateAfter(user: User, now: LocalDateTime): Sanction?
    fun countByUserAndType(user: User, type: SanctionType): Long
    fun findAllByUserAndType(user: User, type: SanctionType): List<Sanction>
}
