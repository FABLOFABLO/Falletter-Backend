package com.example.falletterbackend.falletter.entity.letter.repository

import com.example.falletterbackend.falletter.entity.letter.LetterBox
import org.springframework.data.jpa.repository.JpaRepository

interface LetterBoxRepository : JpaRepository<LetterBox, Long> {
}