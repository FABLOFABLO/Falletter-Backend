package com.example.falletterbackend.falletter.dto.hint.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "힌트 응답")
data class HintResponse(
    @Schema(description = "힌트 ID", example = "1")
    val id: Long,

    @Schema(description = "첫 번째 힌트", example = "ㅇ")
    val firstHint: String,

    @Schema(description = "두 번째 힌트", example = "")
    val secondHint: String,

    @Schema(description = "세 번째 힌트", example = "")
    val thirdHint: String,

    @Schema(description = "사용자 ID", example = "1")
    val userId: Long
)
