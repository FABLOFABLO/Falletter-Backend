package com.example.falletterbackend.falletter.service.auth

import com.example.falletterbackend.common.utils.redis.RedisUtils
import com.example.falletterbackend.falletter.dto.auth.request.AuthMailVerifyRequest
import com.example.falletterbackend.falletter.entity.auth.repository.EmailVerifyRepository
import com.example.falletterbackend.common.error.exception.CustomException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.util.*

@Service
@Transactional
class AuthMailVerifyService(
    private val redisUtils: RedisUtils,
    private val javaMailSender: JavaMailSender,
    private val emailVerifyRepository: EmailVerifyRepository
) {
    private val log = LoggerFactory.getLogger(AuthMailVerifyService::class.java)

    @Value("\${spring.mail.auth-code-expiration-millis}")
    private val authCodeExpirationMillis: Long = 0

    private fun generateVerificationCode(): String {
        val random = Random()
        val code = random.nextInt(888888) + 111111
        return code.toString()
    }

    private fun createEmailForm(email: String, verificationCode: String): SimpleMailMessage {
        return SimpleMailMessage().apply {
            setTo(email)
            setSubject("FALLETTER 이메일 인증 번호")
            setText("FALLETTER 이메일 인증번호 입니다 : $verificationCode 타인에게 공유하지 마세요")
        }
    }

    private fun sendEmail(email: String, verificationCode: String) {
        val emailForm = createEmailForm(email, verificationCode)
        javaMailSender.send(emailForm)
        log.info("이메일 발송 성공: {}", email)
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

        sendEmail(request.email, verificationCode)
    }
}
