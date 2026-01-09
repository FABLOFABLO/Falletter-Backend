package com.example.falletterbackend.falletter.dto.admin.user.response

import com.example.falletterbackend.falletter.entity.suspend.Suspend
import com.example.falletterbackend.falletter.entity.suspend.enums.SuspendType
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "사용자 경고/정지 이력 응답")
data class AdminUserSuspendResponse(
    @Schema(description = "이력 ID", example = "1")
    val id: Long,

    @Schema(description = "유형 (WARNING: 경고, BLOCK: 정지)", example = "BLOCK")
    val type: SuspendType,

    @Schema(description = "정지 일수", example = "7")
    val days: Int?,

    @Schema(description = "사유", example = "부적절한 언어 사용")
    val reason: String?,

    @Schema(description = "시작일시", example = "2024-12-30T10:00:00")
    val startDate: LocalDateTime?,

    @Schema(description = "종료일시", example = "2025-01-06T10:00:00")
    val endDate: LocalDateTime?,

    @Schema(description = "생성일시", example = "2024-12-30T10:00:00")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(entity: Suspend) = AdminUserSuspendResponse(
            id = entity.id,
            type = entity.type,
            days = entity.days,
            reason = entity.blockReason,
            startDate = entity.startDate,
            endDate = entity.endDate,
            createdAt = entity.createdAt
        )
    }
}
