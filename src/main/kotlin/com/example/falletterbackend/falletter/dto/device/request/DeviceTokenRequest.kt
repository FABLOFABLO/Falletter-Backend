package com.example.falletterbackend.falletter.dto.device.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "디바이스 토큰 등록 요청")
data class DeviceTokenRequest(
    @Schema(description = "FCM 토큰", example = "fMJ9k2...")
    val token: String,

    @Schema(description = "디바이스 고유 ID", example = "device-uuid-1234")
    val deviceId: String
)
