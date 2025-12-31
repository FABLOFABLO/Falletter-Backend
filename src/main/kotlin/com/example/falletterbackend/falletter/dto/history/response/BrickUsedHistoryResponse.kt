package com.example.falletterbackend.falletter.dto.history.response

import com.example.falletterbackend.falletter.entity.history.HistoryType
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "벽돌 사용 기록 응답")
data class BrickUsedHistoryResponse(
    @Schema(description = "기록 ID", example = "1")
    val id: Long,

    @Schema(description = "설명", example = "첫 번째 힌트 열람")
    val description: String?,

    @Schema(description = "사용량", example = "10")
    val amount: Long,

    @Schema(description = "기록 타입", example = "QUESTION")
    val type: HistoryType,

    @Schema(description = "질문 내용", example = "가장 친한 친구는?")
    val question: String,

    @Schema(description = "대상 사용자 ID", example = "2")
    val targetUserId: Long,

    @Schema(description = "작성자 ID", example = "3")
    val writerUserId: Long,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "학번", example = "0000")
    val schoolNumber: String,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime
)
