package com.example.falletterbackend.falletter.dto.admin.letter.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "미승인 편지 목록 응답")
data class AdminLetterUnpassedListResponse(
    @Schema(description = "편지 ID", example = "1")
    val id: Long,

    @Schema(description = "승인 여부", example = "false")
    val isPassed: Boolean,

    @Schema(description = "수신자 ID", example = "2")
    val receptionId: Long,

    @Schema(description = "발신자 ID", example = "3")
    val senderId: Long,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime
)
