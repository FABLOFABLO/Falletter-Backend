package com.example.falletterbackend.falletter.dto.item.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "벽돌 수량 변경 요청")
data class ItemBrickItemUpdateRequest(
    @Schema(description = "변경할 벽돌 수량", example = "10")
    val brickUpdate: Long
)
