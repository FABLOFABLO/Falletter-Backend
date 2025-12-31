package com.example.falletterbackend.falletter.dto.history.request

import com.example.falletterbackend.falletter.entity.history.HistoryType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "벽돌 사용 기록 저장 요청")
data class BrickSaveHistoryRequest(
    @Schema(description = "제목", example = "힌트 사용")
    val title: String,

    @Schema(description = "설명", example = "첫 번째 힌트 열람")
    val description: String,

    @Schema(description = "사용량", example = "10")
    val amount: Long,

    @Schema(description = "기록 타입", example = "QUESTION")
    val type: HistoryType,

    @Schema(description = "질문 ID", example = "1")
    val questionId: Long,

    @Schema(description = "대상 사용자 ID", example = "2")
    val targetUserId: Long,

    @Schema(description = "작성자 ID", example = "3")
    val writerUserId: Long
)
