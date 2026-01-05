package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.brick.response.BrickTimerResponse
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class BrickTimerGetRemainingService(
    private val userFacade: UserFacade,
    private val redisUtils: RedisUtils
) {
    companion object {
        private const val BRICK_TIMER_KEY_PREFIX = "brick_timer:"
    }

    fun execute(): BrickTimerResponse {
        val user = userFacade.getCurrentUser()
        val key = "$BRICK_TIMER_KEY_PREFIX${user.id}"
        val ttl = redisUtils.getTtl(key)
        val isActive = ttl > 0
        return BrickTimerResponse(
            userId = user.id,
            remainingSeconds = if (isActive) ttl else 0,
            isActive = isActive
        )
    }
}
