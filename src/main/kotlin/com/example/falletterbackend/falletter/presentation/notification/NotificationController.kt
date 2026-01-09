package com.example.falletterbackend.falletter.presentation.notification

import com.example.falletterbackend.falletter.dto.notification.request.NotificationUpdateRequest
import com.example.falletterbackend.falletter.dto.notification.response.NotificationResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.notification.NotificationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "notification", description = "알림 설정 API")
@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationService: NotificationService
) {
    @Operation(summary = "알림 설정 조회", description = "현재 사용자의 알림 설정을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.NOTIFICATION_GET)
    @ResponseStatus(HttpStatus.OK)
    fun getNotification(): NotificationResponse {
        return notificationService.getNotification()
    }

    @Operation(summary = "알림 설정 수정", description = "현재 사용자의 알림 설정을 수정합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "수정 성공")
    )
    @PatchMapping(RestApiSpec.NOTIFICATION_UPDATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateNotification(@RequestBody @Valid request: NotificationUpdateRequest) {
        notificationService.updateNotification(request)
    }
}
