package com.example.falletterbackend.falletter.service.timer

import com.example.falletterbackend.common.constants.RedisKeyConstants
import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class BrickTimerStartService(
    userFacade: UserFacade,
    redisUtils: RedisUtils
) : BaseTimerStartService(userFacade, redisUtils) {

    override val keyPrefix: String = RedisKeyConstants.BRICK_TIMER_KEY_PREFIX
    override val duration: Duration = RedisKeyConstants.BRICK_TIMER_DURATION
}
