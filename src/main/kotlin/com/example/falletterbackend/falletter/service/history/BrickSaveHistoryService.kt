package com.example.falletterbackend.falletter.service.history

import com.example.falletterbackend.falletter.dto.history.request.BrickSaveHistoryRequest
import com.example.falletterbackend.falletter.entity.history.History
import com.example.falletterbackend.falletter.facade.history.HistoryFacade
import com.example.falletterbackend.falletter.facade.question.QuestionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrickSaveHistoryService(
    private val historyFacade: HistoryFacade,
    private val userFacade: UserFacade,
    private val questionFacade: QuestionFacade
) {
    @Transactional
    fun execute(request: BrickSaveHistoryRequest) {
        val targetUser = userFacade.getUserById(request.targetUserId)
        val writerUser = userFacade.getUserById(request.writerUserId)
        val question = questionFacade.getQuestionById(request.questionId)

        val history = History(
            title = request.title,
            description = request.description,
            amount = request.amount,
            type = request.type,
            question = question,
            targetUserId = targetUser,
            writerUserId = writerUser
        )

        historyFacade.save(history)
    }
}
