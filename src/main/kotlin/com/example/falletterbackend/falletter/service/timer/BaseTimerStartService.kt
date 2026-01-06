package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.facade.user.UserFacade
import java.time.Duration

abstract class BaseTimerStartService(
    protected val userFacade: UserFacade,
    protected val redisUtils: RedisUtils
) {
    protected abstract val keyPrefix: String
    protected abstract val duration: Duration

    fun execute() {
        val user = userFacade.getCurrentUser()
        val key = "$keyPrefix${user.id}"
        redisUtils.setValues(key, "active", duration)
    }
}
