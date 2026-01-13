package com.example.falletterbackend.falletter.entity.suspend.repository

import com.example.falletterbackend.falletter.entity.suspend.Suspend
import com.example.falletterbackend.falletter.entity.suspend.enums.SuspendType
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface SuspendRepository : JpaRepository<Suspend, Long>, SuspendRepositoryCustom {
    fun findAllByUser(user: User): List<Suspend>

    fun findByUserAndEndDateAfter(user: User, now: LocalDateTime): Suspend?

    fun countByUserAndType(user: User, type: SuspendType): Long
}
