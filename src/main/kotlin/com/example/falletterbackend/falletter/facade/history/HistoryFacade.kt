package com.example.falletterbackend.falletter.facade.history

import com.example.falletterbackend.falletter.entity.history.History
import com.example.falletterbackend.falletter.entity.history.repository.HistoryRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.exception.history.HistoryNotFoundException
import org.springframework.stereotype.Component

@Component
class HistoryFacade(
    private val historyRepository: HistoryRepository
) {
    fun getHistoriesByWriterWithRelations(writer: User): List<History> {
        val histories = historyRepository.findAllByWriterUserIdWithRelations(writer)
        if (histories.isEmpty()) {
            throw HistoryNotFoundException
        }
        return histories
    }

    fun save(history: History): History {
        return historyRepository.save(history)
    }
}
