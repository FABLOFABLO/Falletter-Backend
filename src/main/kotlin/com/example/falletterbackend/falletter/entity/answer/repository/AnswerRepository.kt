package com.example.falletterbackend.falletter.entity.answer.repository

import com.example.falletterbackend.falletter.entity.answer.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long>, AnswerRepositoryCustom {
}
