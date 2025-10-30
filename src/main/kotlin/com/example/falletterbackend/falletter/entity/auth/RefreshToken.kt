package com.example.falletterbackend.falletter.entity.auth

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "RefreshToken", timeToLive = 60 * 60 * 2)
class RefreshToken(
    @Id
    val email: String,

    @Indexed
    var token: String,
)
