package com.example.falletterbackend.falletter.service.fcm

import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.facade.device.DeviceTokenFacade
import com.example.falletterbackend.falletter.facade.notification.NotificationFacade
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FcmService(
    private val firebaseMessaging: FirebaseMessaging,
    private val notificationFacade: NotificationFacade,
    private val deviceTokenFacade: DeviceTokenFacade
) {
    private val log = LoggerFactory.getLogger(FcmService::class.java)

    companion object {
        private val INVALID_TOKEN_ERROR_CODES = setOf(
            "UNREGISTERED",
            "INVALID_ARGUMENT"
        )
    }

    fun sendWarningNotification(user: User) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled) {
            sendToAllDevices(user, FcmMessage.Warning.TITLE, FcmMessage.Warning.BODY)
        }
    }

    fun sendBlockNotification(user: User, days: Int) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled) {
            sendToAllDevices(user, FcmMessage.Block.TITLE, FcmMessage.Block.body(days))
        }
    }

    fun sendCommentNotification(user: User) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled && notification.commentEnabled) {
            sendToAllDevices(user, FcmMessage.Comment.TITLE, FcmMessage.Comment.BODY)
        }
    }

    fun sendBrickActivationNotification(user: User) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled && notification.brickActivationEnabled) {
            sendToAllDevices(user, FcmMessage.BrickActivation.TITLE, FcmMessage.BrickActivation.BODY)
        }
    }

    fun sendBrickNotification(user: User) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled && notification.brickEnabled) {
            sendToAllDevices(user, FcmMessage.Brick.TITLE, FcmMessage.Brick.BODY)
        }
    }

    fun sendLetterNotification(user: User) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled && notification.letterEnabled) {
            sendToAllDevices(user, FcmMessage.Letter.TITLE, FcmMessage.Letter.BODY)
        }
    }

    fun sendLetterSentNotification(user: User) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled && notification.letterSentEnabled) {
            sendToAllDevices(user, FcmMessage.LetterSent.TITLE, FcmMessage.LetterSent.BODY)
        }
    }

    fun sendAdminNoticeNotification(user: User, noticeTitle: String) {
        val notification = notificationFacade.getOrCreateByUser(user)
        if (notification.pushEnabled && notification.adminNoticeEnabled) {
            sendToAllDevices(user, FcmMessage.AdminNotice.TITLE, FcmMessage.AdminNotice.body(noticeTitle))
        }
    }

    private fun sendToAllDevices(user: User, title: String, body: String) {
        val tokens = deviceTokenFacade.getTokensByUser(user)
        if (tokens.isEmpty()) {
            log.warn("FCM 전송 스킵 - 등록된 디바이스 토큰 없음 (userId: {})", user.id)
            return
        }
        log.info("FCM 전송 대상 디바이스 수: {} (userId: {})", tokens.size, user.id)
        tokens.forEach { token ->
            sendNotification(token, title, body)
        }
    }

    private fun sendNotification(fcmToken: String, title: String, body: String) {
        log.info("FCM 전송 시도 - token: {}, title: {}, body: {}", fcmToken, title, body)
        try {
            val notification = com.google.firebase.messaging.Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()

            val message = Message.builder()
                .setToken(fcmToken)
                .setNotification(notification)
                .build()

            val response = firebaseMessaging.send(message)
            log.info("FCM 전송 성공 - response: {}", response)
        } catch (e: FirebaseMessagingException) {
            log.warn("FCM 전송 실패 - token: {}, error: {}", fcmToken, e.message)

            if (e.messagingErrorCode?.name in INVALID_TOKEN_ERROR_CODES) {
                log.info("무효 토큰 삭제 - token: {}", fcmToken)
                deviceTokenFacade.deleteByToken(fcmToken)
            }
        }
    }
}
