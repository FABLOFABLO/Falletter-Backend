package com.example.falletterbackend.falletter.dto.admin.notice.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "공지사항 등록 요청")
data class NoticeCreateRequest(
    @Schema(description = "공지사항 제목", example = "서비스 점검 안내")
    val title: String,

    @Schema(description = "공지사항 내용", example = "12월 31일 00:00 ~ 06:00 서비스 점검이 진행됩니다.")
    val content: String
)
