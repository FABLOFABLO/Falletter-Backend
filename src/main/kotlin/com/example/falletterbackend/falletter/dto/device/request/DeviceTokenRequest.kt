package com.example.falletterbackend.falletter.dto.device.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "디바이스 토큰 등록 요청")
data class DeviceTokenRequest(
    @field:NotBlank(message = "FCM 토큰은 필수입니다")
    @Schema(description = "FCM 토큰", example = "fMJ9k2...")
    val token: String,

    @field:NotBlank(message = "디바이스 ID는 필수입니다")
    @Schema(description = "디바이스 고유 ID", example = "device-uuid-1234")
    val deviceId: String
)
