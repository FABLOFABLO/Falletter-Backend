package com.example.falletterbackend.falletter.dto.notification.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "알림 설정 수정 요청")
data class NotificationUpdateRequest(
    @Schema(description = "푸시(전체) 알림 수신 여부", example = "true")
    val pushEnabled: Boolean,

    @Schema(description = "댓글 알림 수신 여부", example = "true")
    val commentEnabled: Boolean,

    @Schema(description = "브릭 활성화 알림 수신 여부", example = "true")
    val brickActivationEnabled: Boolean,

    @Schema(description = "브릭 알림 수신 여부", example = "true")
    val brickEnabled: Boolean,

    @Schema(description = "래터 수신 알림 수신 여부", example = "true")
    val letterEnabled: Boolean,

    @Schema(description = "래터 전송 완료 알림 수신 여부", example = "true")
    val letterSentEnabled: Boolean,

    @Schema(description = "관리자 공지 알림 수신 여부", example = "true")
    val adminNoticeEnabled: Boolean
)
