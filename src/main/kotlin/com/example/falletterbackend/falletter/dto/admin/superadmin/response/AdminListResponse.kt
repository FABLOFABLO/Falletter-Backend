package com.example.falletterbackend.falletter.dto.admin.superadmin.response

import com.example.falletterbackend.falletter.entity.admin.Admin
import com.example.falletterbackend.falletter.entity.admin.enums.AdminRole
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "승인된 관리자 목록 응답")
data class AdminListResponse(
    @Schema(description = "어드민 ID", example = "1")
    val id: Long,

    @Schema(description = "이메일", example = "admin@dsm.hs.kr")
    val email: String,

    @Schema(description = "이름", example = "홍길동")
    val name: String,

    @Schema(description = "성별", example = "MALE")
    val gender: Gender,

    @Schema(description = "역할", example = "ADMIN")
    val role: AdminRole,

    @Schema(description = "승인 일시")
    val approvedAt: LocalDateTime?
) {
    companion object {
        fun from(admin: Admin) = AdminListResponse(
            id = admin.id,
            email = admin.email,
            name = admin.name,
            gender = admin.gender,
            role = admin.role,
            approvedAt = admin.processedAt
        )
    }
}
