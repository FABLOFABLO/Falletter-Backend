package com.example.falletterbackend.falletter.dto.history.request

import com.example.falletterbackend.falletter.entity.history.HistoryType
import com.example.falletterbackend.falletter.entity.question.Question

data class BrickUsedHistoryRequest(
    val title: String,
    val description: String,
    val amount: Long,
    val type: HistoryType,
    val question: Question,
    val targetUserId: Long,
    val writerUserId: Long
)
