package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.constants.RedisKeyConstants
import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.roulette.response.RouletteTimerResponse
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class RouletteTimerGetRemainingService(
    userFacade: UserFacade,
    redisUtils: RedisUtils
) : BaseTimerGetRemainingService(userFacade, redisUtils) {

    override val keyPrefix: String = RedisKeyConstants.ROULETTE_TIMER_KEY_PREFIX

    fun execute(): RouletteTimerResponse {
        val timerInfo = getRemainingTime()
        return RouletteTimerResponse(
            userId = timerInfo.userId,
            remainingSeconds = timerInfo.remainingSeconds,
            isActive = timerInfo.isActive
        )
    }
}
