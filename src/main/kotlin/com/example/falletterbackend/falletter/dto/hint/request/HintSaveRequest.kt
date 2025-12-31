package com.example.falletterbackend.falletter.dto.hint.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "힌트 저장 요청")
data class HintSaveRequest(
    @Schema(description = "답변 ID", example = "1")
    val answerId: Long,

    @Schema(description = "첫 번째 힌트", example = "ㅇ")
    val firstHint: String,

    @Schema(description = "두 번째 힌트", example = "ㅡ")
    val secondHint: String,

    @Schema(description = "세 번째 힌트", example = "ㄴ")
    val thirdHint: String
)
