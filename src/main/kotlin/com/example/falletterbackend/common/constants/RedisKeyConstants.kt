package com.example.falletterbackend.common.constants

import java.time.Duration

object RedisKeyConstants {
    const val BRICK_TIMER_KEY_PREFIX = "brick_timer:"
    const val ROULETTE_TIMER_KEY_PREFIX = "roulette_timer:"

    val BRICK_TIMER_DURATION: Duration = Duration.ofHours(4)
    val ROULETTE_TIMER_DURATION: Duration = Duration.ofHours(24)
}
