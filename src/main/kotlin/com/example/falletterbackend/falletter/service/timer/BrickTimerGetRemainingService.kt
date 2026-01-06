package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.constants.RedisKeyConstants
import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.brick.response.BrickTimerResponse
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class BrickTimerGetRemainingService(
    userFacade: UserFacade,
    redisUtils: RedisUtils
) : BaseTimerGetRemainingService(userFacade, redisUtils) {

    override val keyPrefix: String = RedisKeyConstants.BRICK_TIMER_KEY_PREFIX

    fun execute(): BrickTimerResponse {
        val timerInfo = getRemainingTime()
        return BrickTimerResponse(
            userId = timerInfo.userId,
            remainingSeconds = timerInfo.remainingSeconds,
            isActive = timerInfo.isActive
        )
    }
}
