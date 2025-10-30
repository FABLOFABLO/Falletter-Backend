package com.example.falletterbackend.falletter.dto.question.response

import com.example.falletterbackend.falletter.entity.question.Question

data class QuestionResponse(
    val id: Long,
    val question: String
) {
    companion object {
        fun from(entity: Question): QuestionResponse {
            return QuestionResponse(
                id = entity.id!!,
                question = entity.question
            )
        }
    }
}
