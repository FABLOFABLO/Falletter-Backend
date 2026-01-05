package com.example.falletterbackend.falletter.dto.device.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "디바이스 토큰 등록 응답")
data class DeviceTokenRegisterResponse(
    @Schema(description = "디바이스 ID", example = "1")
    val id: Long
)
