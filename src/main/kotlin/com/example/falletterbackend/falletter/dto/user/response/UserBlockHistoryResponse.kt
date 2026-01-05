package com.example.falletterbackend.falletter.dto.user.response

import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import java.time.LocalDateTime

data class UserBlockHistoryResponse(
    val id: Long,
    val type: BlockType,
    val days: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?
)
