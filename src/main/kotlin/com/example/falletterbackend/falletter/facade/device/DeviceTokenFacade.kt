package com.example.falletterbackend.falletter.facade.device

import com.example.falletterbackend.falletter.entity.device.DeviceToken
import com.example.falletterbackend.falletter.entity.device.repository.DeviceTokenRepository
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.stereotype.Component

@Component
class DeviceTokenFacade(
    private val deviceTokenRepository: DeviceTokenRepository
) {
    fun getAllByUser(user: User): List<DeviceToken> {
        return deviceTokenRepository.findAllByUser(user)
    }

    fun getTokensByUser(user: User): List<String> {
        return deviceTokenRepository.findAllByUser(user).map { it.token }
    }

    fun saveOrUpdate(user: User, token: String, deviceId: String): DeviceToken {
        val existing = deviceTokenRepository.findByUserAndDeviceId(user, deviceId)

        return if (existing != null) {
            existing.updateToken(token)
            existing
        } else {
            deviceTokenRepository.save(DeviceToken(user = user, token = token, deviceId = deviceId))
        }
    }

    fun deleteByUserAndDeviceId(user: User, deviceId: String) {
        deviceTokenRepository.deleteByUserAndDeviceId(user, deviceId)
    }

    fun deleteById(id: Long) {
        deviceTokenRepository.deleteById(id)
    }

    fun deleteAllByUser(user: User) {
        deviceTokenRepository.deleteAllByUser(user)
    }
}
