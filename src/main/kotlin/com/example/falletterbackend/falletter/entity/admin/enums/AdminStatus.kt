package com.example.falletterbackend.falletter.entity.admin.enums

enum class AdminStatus(
    val state: String
) {
    PENDING("대기"),
    APPROVED("승인"),
    REJECTED("반려")
}
