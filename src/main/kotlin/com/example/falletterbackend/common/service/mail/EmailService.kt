package com.example.falletterbackend.common.service.mail

import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    private val log = LoggerFactory.getLogger(EmailService::class.java)

    fun sendVerificationEmail(email: String, verificationCode: String) {
        val emailForm = createEmailForm(
            to = email,
            subject = "FALLETTER 이메일 인증 번호",
            text = "FALLETTER 이메일 인증번호 입니다 : $verificationCode 타인에게 공유하지 마세요"
        )
        javaMailSender.send(emailForm)
        log.info("이메일 발송 성공: {}", email)
    }

    private fun createEmailForm(to: String, subject: String, text: String): SimpleMailMessage {
        return SimpleMailMessage().apply {
            setTo(to)
            setSubject(subject)
            setText(text)
        }
    }
}
