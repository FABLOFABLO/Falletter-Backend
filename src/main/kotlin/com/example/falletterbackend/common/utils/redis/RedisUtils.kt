package com.example.falletterbackend.common.utils.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisUtils(private val redisTemplate: RedisTemplate<String, String>) {

    fun setValues(key: String, data: String) {
        val values: ValueOperations<String, String> = redisTemplate.opsForValue()
        values.set(key, data)
    }

    fun setValues(key: String, data: String, duration: Duration) {
        val values: ValueOperations<String, String> = redisTemplate.opsForValue()
        values.set(key, data, duration)
    }

    fun getValues(key: String): String? {
        return redisTemplate.opsForValue()[key]
    }

    fun deleteValues(key: String): Boolean {
        return redisTemplate.delete(key) ?: false
    }

    fun getTtl(key: String): Long {
        return redisTemplate.getExpire(key) ?: -1L
    }

    fun hasKey(key: String): Boolean {
        return redisTemplate.hasKey(key) ?: false
    }
}
