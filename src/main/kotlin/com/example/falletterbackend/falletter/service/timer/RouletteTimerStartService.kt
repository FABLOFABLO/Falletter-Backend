package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.constants.RedisKeyConstants
import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RouletteTimerStartService(
    userFacade: UserFacade,
    redisUtils: RedisUtils
) : BaseTimerStartService(userFacade, redisUtils) {

    override val keyPrefix: String = RedisKeyConstants.ROULETTE_TIMER_KEY_PREFIX
    override val duration: Duration = RedisKeyConstants.ROULETTE_TIMER_DURATION
}
