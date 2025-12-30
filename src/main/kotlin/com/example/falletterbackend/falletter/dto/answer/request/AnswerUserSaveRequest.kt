package com.example.falletterbackend.falletter.dto.answer.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "답변 저장 요청")
data class AnswerUserSaveRequest(
    @Schema(description = "질문 ID", example = "1")
    val questionId: Long,

    @Schema(description = "대상 사용자 ID", example = "2")
    val targetUser: Long
)
