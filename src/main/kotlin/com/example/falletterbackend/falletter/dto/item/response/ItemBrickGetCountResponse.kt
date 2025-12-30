package com.example.falletterbackend.falletter.dto.item.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "벽돌 수량 조회 응답")
data class ItemBrickGetCountResponse(
    @Schema(description = "사용자 ID", example = "1")
    val userId: Long,

    @Schema(description = "벽돌 수량", example = "100")
    val brickCount: Long
)
