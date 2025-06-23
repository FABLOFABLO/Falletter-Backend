package com.example.falletterbackend.falletter.entity.letterBox.repository

import com.example.falletterbackend.falletter.entity.letterBox.LetterBox
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface LetterBoxRepository : JpaRepository<LetterBox, Long> {
    fun findByIdAndSender(id: Long, sender: User): LetterBox?

    fun findAllBySender(sender: User): List<LetterBox>

    fun findByIdAndReception_Id(letterId: Long, receiverId: Long): LetterBox?

    fun findAllByReception_Id(receiverId: Long): List<LetterBox>
}