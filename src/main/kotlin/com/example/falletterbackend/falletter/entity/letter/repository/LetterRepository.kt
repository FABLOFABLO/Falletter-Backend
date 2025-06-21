package com.example.falletterbackend.falletter.entity.letter.repository

import com.example.falletterbackend.falletter.dto.letter.response.LetterGetCountResponse
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long>{
    fun findFirstByUserOrderByIdAsc(user: User): Letter?

    fun findByUser(user: User): LetterGetCountResponse

    fun findEntityByUser(user: User): Letter?
}