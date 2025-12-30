package com.example.falletterbackend.falletter.dto.item.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "편지 수량 변경 요청")
data class ItemLetterItemUpdateRequest(
    @Schema(description = "변경할 편지 수량", example = "5")
    val letterUpdate: Long
)
