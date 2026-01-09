package com.example.falletterbackend.falletter.dto.admin.user.response

import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 프로필 상세 응답")
data class AdminUserProfileResponse(
    @Schema(description = "사용자 ID", example = "1")
    val id: Long,

    @Schema(description = "학번", example = "0000")
    val schoolNumber: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    val profileImage: String?,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "경고 횟수", example = "2")
    val warningCount: Long,

    @Schema(description = "경고/정지 이력 목록")
    val suspends: List<AdminUserSuspendResponse>
) {
    companion object {
        fun from(entity: User, warningCount: Long, suspends: List<AdminUserSuspendResponse>) = AdminUserProfileResponse(
            id = entity.id,
            schoolNumber = entity.schoolNumber,
            name = entity.name,
            profileImage = entity.profileImage,
            gender = entity.gender,
            warningCount = warningCount,
            suspends = suspends
        )
    }
}
