package com.example.falletterbackend.falletter.dto.roulette.response

data class RouletteTimerResponse(
    val userId: Long,
    val remainingSeconds: Long,
    val isActive: Boolean
)
