package com.example.falletterbackend.falletter.dto.admin.user.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 정지 요청")
data class AdminUserSuspendRequest(
    @Schema(description = "정지 일수", example = "7")
    val days: Int,

    @Schema(description = "정지 사유", example = "부적절한 언어 사용")
    val reason: String
)
