package com.example.falletterbackend.falletter.dto.hint.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "힌트 수정 요청")
data class HintUpdateRequest(
    @Schema(description = "힌트 ID", example = "1")
    val hintId: Long,

    @Schema(description = "첫 번째 힌트", example = "키가 170cm 이상이에요")
    val firstHint: String,

    @Schema(description = "두 번째 힌트", example = "같은 반이에요")
    val secondHint: String,

    @Schema(description = "세 번째 힌트", example = "이름이 ㄱ으로 시작해요")
    val thirdHint: String
)