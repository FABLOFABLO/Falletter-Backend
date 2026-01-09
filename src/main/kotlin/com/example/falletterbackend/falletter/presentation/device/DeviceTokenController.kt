package com.example.falletterbackend.falletter.presentation.device

import com.example.falletterbackend.falletter.dto.device.request.DeviceTokenRequest
import com.example.falletterbackend.falletter.dto.device.response.DeviceTokenRegisterResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.device.DeviceTokenService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "fcm_device", description = "디바이스 토큰 API")
@RestController
@RequestMapping("/device")
class DeviceTokenController(
    private val deviceTokenService: DeviceTokenService
) {
    @Operation(summary = "디바이스 토큰 등록", description = "FCM 토큰을 등록합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "등록 성공")
    )
    @PostMapping(RestApiSpec.DEVICE_TOKEN_REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    fun registerToken(@RequestBody @Valid request: DeviceTokenRequest): DeviceTokenRegisterResponse {
        return deviceTokenService.registerToken(request)
    }

    @Operation(summary = "디바이스 토큰 삭제", description = "FCM 토큰을 삭제합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "삭제 성공")
    )
    @DeleteMapping(RestApiSpec.DEVICE_TOKEN_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteToken(@PathVariable("device-id") id: Long) {
        deviceTokenService.deleteToken(id)
    }
}
