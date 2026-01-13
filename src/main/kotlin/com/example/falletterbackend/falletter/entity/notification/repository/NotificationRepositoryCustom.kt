package com.example.falletterbackend.falletter.entity.notification.repository

import com.example.falletterbackend.falletter.entity.notification.Notification
import com.example.falletterbackend.falletter.entity.user.User

interface NotificationRepositoryCustom {
    fun findByUser(user: User): Notification?

    fun existsByUser(user: User): Boolean
}
