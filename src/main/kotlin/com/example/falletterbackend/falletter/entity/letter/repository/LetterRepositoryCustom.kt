package com.example.falletterbackend.falletter.entity.letter.repository

import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.user.User
import java.time.LocalDateTime

interface LetterRepositoryCustom {
    fun findAllUnpassedWithRelations(): List<Letter>

    fun findAllByReceptionIdAndPassedWithSender(receiverId: Long): List<Letter>

    fun findAllBySenderAndPassedWithReception(sender: User): List<Letter>

    fun findAllUndeliveredBefore(threshold: LocalDateTime): List<Letter>
}
