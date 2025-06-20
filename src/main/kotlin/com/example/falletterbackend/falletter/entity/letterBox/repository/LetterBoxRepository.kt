package com.example.falletterbackend.falletter.entity.letterBox.repository

import com.example.falletterbackend.falletter.entity.letterBox.LetterBox
import org.springframework.data.jpa.repository.JpaRepository

interface LetterBoxRepository : JpaRepository<LetterBox, Long> {
}