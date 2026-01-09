package com.example.falletterbackend.falletter.dto.answer.response

import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "선택한 답변 응답")
data class AnswerUserChosenResponse(
    @Schema(description = "답변 ID", example = "1")
    val id: Long,

    @Schema(description = "학번", example = "0000")
    val schoolNumber: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "질문 내용", example = "가장 친한 친구는?")
    val question: String,

    @Schema(description = "이모지", example = "😊")
    val emoji: String,

    @Schema(description = "질문 ID", example = "1")
    val questionId: Long,

    @Schema(description = "대상 사용자 ID", example = "2")
    val targetUserId: Long,

    @Schema(description = "작성자 ID", example = "3")
    val writerUserId: Long,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(entity: Answer) = AnswerUserChosenResponse(
            id = entity.id,
            questionId = entity.question.id,
            question = entity.question.question,
            emoji = entity.question.emoji,
            targetUserId = entity.targetUserId.id,
            writerUserId = entity.writerId.id,
            gender = entity.writerId.gender,
            schoolNumber = entity.writerId.schoolNumber,
            name = entity.writerId.name,
            createdAt = entity.createdAt
        )
    }
}
