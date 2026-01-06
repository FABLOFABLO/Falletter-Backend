package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.facade.user.UserFacade

abstract class BaseTimerGetRemainingService(
    protected val userFacade: UserFacade,
    protected val redisUtils: RedisUtils
) {
    protected abstract val keyPrefix: String

    protected fun getRemainingTime(): TimerInfo {
        val user = userFacade.getCurrentUser()
        val key = "$keyPrefix${user.id}"
        val ttl = redisUtils.getTtl(key)
        val isActive = ttl > 0
        return TimerInfo(
            userId = user.id,
            remainingSeconds = if (isActive) ttl else 0,
            isActive = isActive
        )
    }

    data class TimerInfo(
        val userId: Long,
        val remainingSeconds: Long,
        val isActive: Boolean
    )
}
