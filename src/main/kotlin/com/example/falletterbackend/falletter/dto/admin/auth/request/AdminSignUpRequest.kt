package com.example.falletterbackend.falletter.dto.admin.auth.request

import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "어드민 가입 신청 요청")
data class AdminSignUpRequest(
    @Schema(description = "이메일", example = "admin@dsm.hs.kr")
    val email: String,

    @Schema(description = "비밀번호", example = "!!password123")
    val password: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,
)
