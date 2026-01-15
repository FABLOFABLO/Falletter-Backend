package com.example.falletterbackend.falletter.dto.user.request

import com.example.falletterbackend.falletter.entity.user.enums.Gender
import com.example.falletterbackend.falletter.entity.user.enums.Theme
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원가입 요청")
data class UserSignUpRequest(
    @Schema(description = "이메일", example = "user@dsm.hs.kr")
    val email: String,

    @Schema(description = "비밀번호", example = "!!password123")
    val password: String,

    @Schema(description = "학번", example = "0000")
    val schoolNumber: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "테마", example = "PINK")
    val theme: Theme,

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    val profileImage: String?,

    @Schema(description = "팔레터 이용약관 동의", example = "true")
    val serviceTerms: Boolean,

    @Schema(description = "개인정보 수집 이용 동의", example = "true")
    val privacyPolicy: Boolean,

    @Schema(description = "커뮤니티 이용약관 동의", example = "true")
    val communityTerms: Boolean,

    @Schema(description = "푸시알림 동의", example = "false")
    val pushNotification: Boolean
)
