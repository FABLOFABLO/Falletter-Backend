package com.example.falletterbackend.falletter.service.history

import com.example.falletterbackend.falletter.dto.history.response.BrickUsedHistoryResponse
import com.example.falletterbackend.falletter.facade.history.HistoryFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrickUsedHistoryService(
    private val historyFacade: HistoryFacade,
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<BrickUsedHistoryResponse> {
        val user = userFacade.getCurrentUser()

        val histories = historyFacade.getHistoriesByWriterWithRelations(user)

        return histories.mapNotNull {
            it.question?.let { question ->
                BrickUsedHistoryResponse(
                    id = it.id,
                    description = it.description,
                    amount = it.amount,
                    type = it.type,
                    question = it.question.question,
                    targetUserId = it.targetUserId.id,
                    writerUserId = it.writerUserId.id,
                    gender = it.targetUserId.gender,
                    schoolNumber = it.targetUserId.schoolNumber,
                    createdAt = it.createdAt
                )
            }
        }
    }
}
