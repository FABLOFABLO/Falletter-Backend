package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RouletteTimerStartService(
    private val userFacade: UserFacade,
    private val redisUtils: RedisUtils
) {
    companion object {
        const val ROULETTE_TIMER_KEY_PREFIX = "roulette_timer:"
        private val TIMER_DURATION = Duration.ofHours(24)
    }

    fun execute() {
        val user = userFacade.getCurrentUser()
        val key = "$ROULETTE_TIMER_KEY_PREFIX${user.id}"
        redisUtils.setValues(key, "active", TIMER_DURATION)
    }
}
