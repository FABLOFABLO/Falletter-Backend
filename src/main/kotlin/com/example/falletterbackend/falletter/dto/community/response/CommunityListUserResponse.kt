package com.example.falletterbackend.falletter.dto.community.response

import com.example.falletterbackend.falletter.entity.user.User
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 정보")
data class CommunityListUserResponse(
    @Schema(description = "사용자 ID", example = "1")
    val userId: Long,

    @Schema(description = "이름", example = "홍길동")
    val name: String
) {
    companion object {
        fun from(user: User): CommunityListUserResponse {
            return CommunityListUserResponse(
                userId = user.id,
                name = user.name
            )
        }
    }
}
