package com.example.falletterbackend.falletter.entity.question.repository

import com.example.falletterbackend.falletter.entity.question.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long>{
}
