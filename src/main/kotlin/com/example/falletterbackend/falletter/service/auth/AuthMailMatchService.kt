package com.example.falletterbackend.falletter.service.auth

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.auth.request.AuthMailMatchRequest
import com.example.falletterbackend.falletter.exception.auth.UnExistVerifyCodeException
import com.example.falletterbackend.falletter.exception.auth.UnMatchVerifyCodeException
import org.springframework.stereotype.Service

@Service
class AuthMailMatchService(
    private val redisUtils: RedisUtils
) {
    fun execute(request: AuthMailMatchRequest) {
        val redisAuthCode = redisUtils.getValues(request) ?: throw UnExistVerifyCodeException
        if (redisAuthCode != request.verifyCode) {
            throw UnMatchVerifyCodeException
        }
    }
}
