package com.example.falletterbackend.falletter.entity.device.repository

import com.example.falletterbackend.falletter.entity.device.DeviceToken
import com.example.falletterbackend.falletter.entity.user.User

interface DeviceTokenRepositoryCustom {
    fun findAllByUser(user: User): List<DeviceToken>

    fun findByUserAndDeviceId(user: User, deviceId: String): DeviceToken?

    fun deleteByUserAndDeviceId(user: User, deviceId: String): Long

    fun deleteAllByUser(user: User): Long

    fun deleteByToken(token: String): Long
}
