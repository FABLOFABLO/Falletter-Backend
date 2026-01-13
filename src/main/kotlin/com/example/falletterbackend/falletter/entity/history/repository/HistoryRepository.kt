package com.example.falletterbackend.falletter.entity.history.repository

import com.example.falletterbackend.falletter.entity.history.History
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryRepository : JpaRepository<History, Long>, HistoryRepositoryCustom {
}
