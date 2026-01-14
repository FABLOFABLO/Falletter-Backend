package com.example.falletterbackend.falletter.dto.admin.superadmin.response

import com.example.falletterbackend.falletter.entity.admin.Admin
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "어드민 가입 신청 목록 응답")
data class AdminRequestListResponse(
    @Schema(description = "어드민 ID", example = "1")
    val id: Long,

    @Schema(description = "이메일", example = "admin@dsm.hs.kr")
    val email: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "상태", example = "PENDING")
    val status: AdminStatus,

    @Schema(description = "신청 일시")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(admin: Admin) = AdminRequestListResponse(
            id = admin.id,
            email = admin.email,
            name = admin.name,
            gender = admin.gender,
            status = admin.status,
            createdAt = admin.createdAt
        )
    }
}
