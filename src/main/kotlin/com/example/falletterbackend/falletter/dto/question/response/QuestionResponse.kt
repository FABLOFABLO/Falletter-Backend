package com.example.falletterbackend.falletter.dto.question.response

import com.example.falletterbackend.falletter.entity.question.Question
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "질문 응답")
data class QuestionResponse(
    @Schema(description = "질문 ID", example = "1")
    val id: Long,

    @Schema(description = "질문 내용", example = "가장 친한 친구는?")
    val question: String,

    @Schema(description = "이모지", example = "😊")
    val emoji: String
) {
    companion object {
        fun from(entity: Question): QuestionResponse {
            return QuestionResponse(
                id = entity.id!!,
                question = entity.question,
                emoji = entity.emoji
            )
        }
    }
}
