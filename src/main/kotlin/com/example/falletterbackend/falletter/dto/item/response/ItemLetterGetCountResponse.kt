package com.example.falletterbackend.falletter.dto.item.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "편지 수량 조회 응답")
data class ItemLetterGetCountResponse(
    @Schema(description = "사용자 ID", example = "1")
    val userId: Long,

    @Schema(description = "편지 수량", example = "10")
    val letterCount: Long
)
