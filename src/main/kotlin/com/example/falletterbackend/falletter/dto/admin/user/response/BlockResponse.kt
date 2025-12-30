package com.example.falletterbackend.falletter.dto.admin.user.response

import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import java.time.LocalDateTime

data class BlockResponse(
    val id: Long,
    val type: BlockType,
    val days: Int?,
    val reason: String?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val createdAt: LocalDateTime
)
