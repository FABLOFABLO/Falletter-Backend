package com.example.falletterbackend.falletter.service.auth

import com.example.falletterbackend.common.service.mail.EmailService
import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.auth.request.AuthMailVerifyRequest
import com.example.falletterbackend.falletter.entity.auth.repository.EmailVerifyRepository
import com.example.falletterbackend.common.error.exception.CustomException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.util.*

@Service
@Transactional
class AuthMailVerifyService(
    private val redisUtils: RedisUtils,
    private val emailService: EmailService,
    private val emailVerifyRepository: EmailVerifyRepository
) {
    @Value("\${spring.mail.auth-code-expiration-millis}")
    private val authCodeExpirationMillis: Long = 0

    private fun generateVerificationCode(): String {
        val random = Random()
        val code = random.nextInt(888888) + 111111
        return code.toString()
    }

    fun execute(request: AuthMailVerifyRequest) {
        if (emailVerifyRepository.existsByEmail(request.email)) {
            throw CustomException(HttpStatus.CONFLICT, "중복 된 이메일 입니다.")
        }

        val verificationCode = generateVerificationCode()

        redisUtils.setValues(
            request.email,
            verificationCode,
            Duration.ofMillis(authCodeExpirationMillis)
        )

        emailService.sendVerificationEmail(request.email, verificationCode)
    }
}
