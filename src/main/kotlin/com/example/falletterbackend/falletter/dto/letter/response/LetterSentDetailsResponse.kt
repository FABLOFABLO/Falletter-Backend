package com.example.falletterbackend.falletter.dto.letter.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "보낸 편지 상세 응답")
data class LetterSentDetailsResponse(
    @Schema(description = "편지 ID", example = "1")
    val id: Long,

    @Schema(description = "편지 내용", example = "안녕하세요, 잘 지내시나요?")
    val content: String,

    @Schema(description = "수신자 ID", example = "2")
    val receptionId: Long,

    @Schema(description = "발신자 ID", example = "3")
    val senderId: Long,

    @Schema(description = "배달 완료 여부", example = "true")
    val isDelivered: Boolean,

    @Schema(description = "승인 여부", example = "true")
    val isPassed: Boolean,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime
)
