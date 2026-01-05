package com.example.falletterbackend.falletter.service.notification

import com.example.falletterbackend.falletter.dto.notification.response.NotificationResponse
import com.example.falletterbackend.falletter.facade.notification.NotificationFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificationGetService(
    private val userFacade: UserFacade,
    private val notificationFacade: NotificationFacade
) {
    @Transactional(readOnly = true)
    fun execute(): NotificationResponse {
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
}
