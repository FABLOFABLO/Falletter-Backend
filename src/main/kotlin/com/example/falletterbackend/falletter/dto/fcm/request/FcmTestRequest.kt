package com.example.falletterbackend.falletter.dto.fcm.request

data class FcmTestRequest(
    val userId: Long,
    val type: FcmTestType,
    val days: Int? = null,
    val noticeTitle: String? = null
)

enum class FcmTestType {
    WARNING,
    BLOCK,
    COMMENT,
    BRICK_ACTIVATION,
    BRICK,
    LETTER,
    LETTER_SENT,
    ADMIN_NOTICE
}
