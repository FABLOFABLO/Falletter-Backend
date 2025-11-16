package com.example.falletterbackend.falletter.service.history

import com.example.falletterbackend.falletter.dto.history.request.BrickSaveHistoryRequest
import com.example.falletterbackend.falletter.entity.history.History
import com.example.falletterbackend.falletter.entity.history.repository.HistoryRepository
import com.example.falletterbackend.falletter.entity.question.repository.QuestionRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.question.QuestionNotFoundException
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrickSaveHistoryService(
    private val historyRepository: HistoryRepository,
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(request: BrickSaveHistoryRequest) {
        val targetUser = userRepository.findByIdOrNull(request.targetUserId)
            ?: throw UserNotFoundException

        val writerUser = userRepository.findByIdOrNull(request.writerUserId)
            ?: throw UserNotFoundException

        val question = questionRepository.findByIdOrNull(request.questionId)
            ?: throw QuestionNotFoundException

        val history = History(
            title = request.title,
            description = request.description,
            amount = request.amount,
            type = request.type,
            question = question,
            targetUserId = targetUser,
            writerUserId = writerUser
        )

        historyRepository.save(history)
    }
}
