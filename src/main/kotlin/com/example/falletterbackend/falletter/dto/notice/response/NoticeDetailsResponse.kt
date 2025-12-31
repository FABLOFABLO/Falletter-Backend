package com.example.falletterbackend.falletter.dto.notice.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "공지사항 상세 응답")
data class NoticeDetailsResponse(
    @Schema(description = "공지사항 ID", example = "1")
    val id: Long,

    @Schema(description = "공지사항 제목", example = "서비스 점검 안내")
    val title: String,

    @Schema(description = "공지사항 내용", example = "12월 31일 00:00 ~ 06:00 서비스 점검이 진행됩니다.")
    val content: String,

    @Schema(description = "작성자 이름", example = "유하은")
    val authorName: String,

    @Schema(description = "작성일시", example = "2025-12-30T14:00:00")
    val createdAt: LocalDateTime
)
