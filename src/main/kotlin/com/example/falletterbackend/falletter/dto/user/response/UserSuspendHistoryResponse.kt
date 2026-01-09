package com.example.falletterbackend.falletter.dto.user.response

import com.example.falletterbackend.falletter.entity.suspend.Suspend
import com.example.falletterbackend.falletter.entity.suspend.enums.SuspendType
import java.time.LocalDateTime

data class UserSuspendHistoryResponse(
    val id: Long,
    val type: SuspendType,
    val days: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?
) {
    companion object {
        fun from(entity: Suspend) = UserSuspendHistoryResponse(
            id = entity.id,
            type = entity.type,
            days = entity.days,
            startDate = entity.startDate,
            endDate = entity.endDate
        )
    }
}
