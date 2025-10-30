package com.example.falletterbackend.falletter.entity.auth

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "tbl_email_verify_number", timeToLive = 60 * 60 * 2)
class EmailVerify(
    @Id
    val email: String,

    val verifyCode: String
)
