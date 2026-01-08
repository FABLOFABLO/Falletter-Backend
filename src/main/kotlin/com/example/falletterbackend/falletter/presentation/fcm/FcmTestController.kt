package com.example.falletterbackend.falletter.presentation.fcm

import com.example.falletterbackend.falletter.dto.fcm.request.FcmTestRequest
import com.example.falletterbackend.falletter.dto.fcm.request.FcmTestType
import com.example.falletterbackend.falletter.facade.user.UserFacade
import com.example.falletterbackend.falletter.service.fcm.FcmService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "fcm_test", description = "FCM 테스트 API (개발용)")
@RestController
@RequestMapping("/test/fcm")
class FcmTestController(
    private val fcmService: FcmService,
    private val userFacade: UserFacade
) {
    @Operation(
        summary = "FCM 푸시 알림 테스트",
        description = """
            지정한 userId의 사용자에게 테스트 푸시 알림을 전송합니다.

            type 종류:
            - WARNING: 경고 알림
            - BLOCK: 정지 알림 (days 필수)
            - COMMENT: 새 댓글 알림
            - BRICK_ACTIVATION: 브릭 활성화 알림
            - BRICK: 새 브릭 알림
            - LETTER: 새 래터 도착 알림
            - LETTER_SENT: 래터 전송 완료 알림
            - ADMIN_NOTICE: 관리자 공지 알림 (noticeTitle 필수)
        """
    )
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    fun sendTestNotification(@RequestBody request: FcmTestRequest): Map<String, String> {
        val user = userFacade.getUserById(request.userId)

        when (request.type) {
            FcmTestType.WARNING -> fcmService.sendWarningNotification(user)
            FcmTestType.BLOCK -> {
                val days = request.days ?: 7
                fcmService.sendBlockNotification(user, days)
            }
            FcmTestType.COMMENT -> fcmService.sendCommentNotification(user)
            FcmTestType.BRICK_ACTIVATION -> fcmService.sendBrickActivationNotification(user)
            FcmTestType.BRICK -> fcmService.sendBrickNotification(user)
            FcmTestType.LETTER -> fcmService.sendLetterNotification(user)
            FcmTestType.LETTER_SENT -> fcmService.sendLetterSentNotification(user)
            FcmTestType.ADMIN_NOTICE -> {
                val title = request.noticeTitle ?: "테스트 공지"
                fcmService.sendAdminNoticeNotification(user, title)
            }
        }

        return mapOf(
            "status" to "success",
            "message" to "${request.type} 알림이 전송되었습니다."
        )
    }
}
