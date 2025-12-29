package com.example.falletterbackend.falletter.entity.letter.repository

import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long> {
    fun findByIdAndSender(id: Long, sender: User): Letter?

    fun findAllBySender(sender: User): List<Letter>

    fun findByIdAndReception_Id(letterId: Long, receiverId: Long): Letter?

    fun findAllByReception_Id(receiverId: Long): List<Letter>

    fun findAllByReception_IdAndIsPassed(receiverId: Long, isPassed: Boolean): List<Letter>
}
