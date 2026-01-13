package com.example.falletterbackend.falletter.entity.letter.repository

import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long>, LetterRepositoryCustom {
    fun findByIdAndSender(id: Long, sender: User): Letter?

    fun findAllBySender(sender: User): List<Letter>

    fun findByIdAndReception_Id(letterId: Long, receiverId: Long): Letter?

    fun findAllByReception_Id(receiverId: Long): List<Letter>

    fun findAllByIsPassed(isPassed: Boolean): List<Letter>

    fun findByIdAndIsPassed(letterId: Long, isPassed: Boolean): Letter?
}
