package com.example.falletterbackend.falletter.entity.history.repository

import com.example.falletterbackend.falletter.entity.history.History
import com.example.falletterbackend.falletter.entity.user.User

interface HistoryRepositoryCustom {
    fun findAllByWriterUserIdWithRelations(writer: User): List<History>
}
