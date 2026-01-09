package com.example.falletterbackend.falletter.facade.notification

import com.example.falletterbackend.falletter.entity.notification.Notification
import com.example.falletterbackend.falletter.entity.notification.repository.NotificationRepository
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.stereotype.Component

@Component
class NotificationFacade(
    private val notificationRepository: NotificationRepository
) {
    fun getOrCreateByUser(user: User): Notification {
        return notificationRepository.findByUser(user)
            ?: notificationRepository.save(Notification(user = user))
    }

    fun save(notification: Notification): Notification {
        return notificationRepository.save(notification)
    }

    fun existsByUser(user: User): Boolean {
        return notificationRepository.existsByUser(user)
    }
}
