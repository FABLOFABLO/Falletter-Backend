package com.example.falletterbackend.falletter.dto.user.response

import com.example.falletterbackend.falletter.entity.user.enums.Gender
import com.example.falletterbackend.falletter.entity.user.enums.Theme
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 정보 응답")
data class UserInfoResponse(
    @Schema(description = "사용자 ID", example = "1")
    val id: Long,

    @Schema(description = "이메일", example = "user@dsn.hs.kr")
    val email: String,

    @Schema(description = "학번", example = "2401")
    val schoolNumber: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "테마", example = "PINK")
    val theme: Theme,

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    val profileImage: String
)
