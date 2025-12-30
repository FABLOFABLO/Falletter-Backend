package com.example.falletterbackend.falletter.dto.letter.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "편지 발송 요청")
data class LetterSentRequest(
    @Schema(description = "편지 내용", example = "안녕하세요, 잘 지내시나요?")
    val content: String,

    @Schema(description = "수신자 ID", example = "2")
    val reception: Long
)
