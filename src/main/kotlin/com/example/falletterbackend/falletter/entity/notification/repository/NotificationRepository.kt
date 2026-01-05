package com.example.falletterbackend.falletter.entity.notification.repository

import com.example.falletterbackend.falletter.entity.notification.Notification
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByUser(user: User): Notification?

    fun existsByUser(user: User): Boolean
}
