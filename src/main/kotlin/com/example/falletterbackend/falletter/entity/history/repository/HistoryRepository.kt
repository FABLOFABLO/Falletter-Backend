package com.example.falletterbackend.falletter.entity.history.repository

import com.example.falletterbackend.falletter.entity.history.History
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryRepository : JpaRepository<History, Long> {
    fun findAllByWriterUserId(writer: User): List<History>
}
