package com.example.falletterbackend.falletter.dto.admin.superadmin.request

import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "어드민 성별 수정 요청")
data class AdminGenderUpdateRequest(
    @Schema(description = "성별", example = "FEMALE")
    val gender: Gender
)
