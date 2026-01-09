package com.example.falletterbackend.falletter.service.notification

import com.example.falletterbackend.falletter.dto.notification.request.NotificationUpdateRequest
import com.example.falletterbackend.falletter.dto.notification.response.NotificationResponse
import com.example.falletterbackend.falletter.facade.notification.NotificationFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificationService(
    private val userFacade: UserFacade,
    private val notificationFacade: NotificationFacade
) {
    @Transactional
    fun getNotification(): NotificationResponse {
        val user = userFacade.getCurrentUser()
        val notification = notificationFacade.getOrCreateByUser(user)

        return NotificationResponse(
            pushEnabled = notification.pushEnabled,
            commentEnabled = notification.commentEnabled,
            brickActivationEnabled = notification.brickActivationEnabled,
            brickEnabled = notification.brickEnabled,
            letterEnabled = notification.letterEnabled,
            letterSentEnabled = notification.letterSentEnabled,
            adminNoticeEnabled = notification.adminNoticeEnabled
        )
    }

    @Transactional
    fun updateNotification(request: NotificationUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val notification = notificationFacade.getOrCreateByUser(user)

        notification.pushEnabled = request.pushEnabled
        notification.commentEnabled = request.commentEnabled
        notification.brickActivationEnabled = request.brickActivationEnabled
        notification.brickEnabled = request.brickEnabled
        notification.letterEnabled = request.letterEnabled
        notification.letterSentEnabled = request.letterSentEnabled
        notification.adminNoticeEnabled = request.adminNoticeEnabled

        notificationFacade.save(notification)
    }
}
