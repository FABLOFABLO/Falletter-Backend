package com.example.falletterbackend.falletter.entity.notification.repository

import com.example.falletterbackend.falletter.entity.notification.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long>, NotificationRepositoryCustom {
}
