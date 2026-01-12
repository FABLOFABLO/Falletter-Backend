package com.example.falletterbackend.falletter.entity.device.repository

import com.example.falletterbackend.falletter.entity.device.DeviceToken
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceTokenRepository : JpaRepository<DeviceToken, Long> {
    fun findAllByUser(user: User): List<DeviceToken>

    fun findByUserAndDeviceId(user: User, deviceId: String): DeviceToken?

    fun existsByUserAndDeviceId(user: User, deviceId: String): Boolean

    fun deleteByUserAndDeviceId(user: User, deviceId: String)

    fun deleteAllByUser(user: User)

    fun deleteByToken(token: String)
}
