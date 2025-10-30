package com.example.falletterbackend.falletter.service.auth

import com.example.falletterbackend.common.utils.RedisUtils
import com.example.falletterbackend.falletter.dto.auth.request.AuthMailVerifyRequest
import com.example.falletterbackend.falletter.entity.auth.repository.EmailVerifyRepository
import com.example.falletterbackend.falletter.exception.auth.CustomException
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.util.*

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class AuthMailVerifyService(
    private val redisUtils: RedisUtils,
    private val javaMailSender: JavaMailSender,
    private val emailVerifyRepository: EmailVerifyRepository
) {
    private val log = LoggerFactory.getLogger(AuthMailVerifyService::class.java)

    @Value("\${spring.mail.auth-code-expiration-millis}")
    private val authCodeExpirationMillis: Long = 0

    fun makeRandomNumber(): String {
        val random = Random()
        val checkNum = random.nextInt(888888) + 111111
        return checkNum.toString()
    }

    val random = makeRandomNumber()

    private fun createEmailForm(request: AuthMailVerifyRequest): SimpleMailMessage {
        return SimpleMailMessage().apply {
            setTo(request.email)
            setSubject("FALLETTER 이메일 인증 번호")
            setText("FALLETTER 이메일 인증번호 입니다 : $random 타인에게 공유하지 마세요")
        }
    }

    fun sendEmail(request: AuthMailVerifyRequest) {
        val emailForm = createEmailForm(request)
        try {
            javaMailSender.send(emailForm)
            log.info("이메일 발송 성공")
        } catch (e: Exception) {
            log.error("이메일 발송 오류", e)
        }
    }

    fun execute(request: AuthMailVerifyRequest) {
        if (emailVerifyRepository.existsByEmail(request.email)) {
            throw CustomException(HttpStatus.CONFLICT, "중복 된 이메일 입니다.")
        }
        redisUtils.setValues(
            request.email,
            random,
            Duration.ofMillis(authCodeExpirationMillis)
        )
        sendEmail(request)
    }
}
