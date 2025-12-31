package com.example.falletterbackend.falletter.dto.user.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "학생 목록 응답")
data class UserGetAllStudentResponse(
    @Schema(description = "사용자 ID", example = "1")
    val id: Long,

    @Schema(description = "학번", example = "0000")
    val schoolNumber: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String
)
