package com.example.falletterbackend.falletter.dto.hint.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "힌트 수정 요청")
data class HintUpdateRequest(
    @Schema(description = "힌트 ID", example = "1")
    val hintId: Long,

    @Schema(description = "첫 번째 힌트", example = "ㅇ")
    val firstHint: String,

    @Schema(description = "두 번째 힌트", example = "ㅡ")
    val secondHint: String,

    @Schema(description = "세 번째 힌트", example = "")
    val thirdHint: String
)