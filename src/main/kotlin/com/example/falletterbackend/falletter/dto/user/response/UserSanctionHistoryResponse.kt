package com.example.falletterbackend.falletter.dto.user.response

import com.example.falletterbackend.falletter.entity.sanction.enums.SanctionType
import java.time.LocalDateTime

data class UserSanctionHistoryResponse(
    val id: Long,
    val type: SanctionType,
    val days: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?
)
