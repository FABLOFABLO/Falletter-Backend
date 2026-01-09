package com.example.falletterbackend.falletter.dto.user.response

import com.example.falletterbackend.falletter.entity.suspend.Suspend

data class UserSuspendReasonResponse(
    val id: Long,
    val reason: String?
) {
    companion object {
        fun from(entity: Suspend) = UserSuspendReasonResponse(
            id = entity.id,
            reason = entity.blockReason
        )
    }
}
