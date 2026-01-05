package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.roulette.response.RouletteTimerResponse
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class RouletteTimerGetRemainingService(
    private val userFacade: UserFacade,
    private val redisUtils: RedisUtils
) {
    companion object {
        private const val ROULETTE_TIMER_KEY_PREFIX = "roulette_timer:"
    }

    fun execute(): RouletteTimerResponse {
        val user = userFacade.getCurrentUser()
        val key = "$ROULETTE_TIMER_KEY_PREFIX${user.id}"
        val ttl = redisUtils.getTtl(key)
        val isActive = ttl > 0
        return RouletteTimerResponse(
            userId = user.id,
            remainingSeconds = if (isActive) ttl else 0,
            isActive = isActive
        )
    }
}
