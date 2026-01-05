package com.example.falletterbackend.falletter.dto.brick.response

data class BrickTimerResponse(
    val userId: Long,
    val remainingSeconds: Long,
    val isActive: Boolean
)
