package com.example.falletterbackend.falletter.facade.device

import com.example.falletterbackend.falletter.entity.device.DeviceToken
import com.example.falletterbackend.falletter.entity.device.repository.DeviceTokenRepository
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class DeviceTokenFacade(
    private val deviceTokenRepository: DeviceTokenRepository
) {
    fun getTokensByUser(user: User): List<String> {
        return deviceTokenRepository.findAllByUser(user).map { it.token }
    }

    fun saveOrUpdate(user: User, token: String, deviceId: String): DeviceToken {
        val existing = deviceTokenRepository.findByUserAndDeviceId(user, deviceId)

        return if (existing != null) {
            existing.updateToken(token)
            existing
        } else {
            try {
                deviceTokenRepository.save(DeviceToken(user = user, token = token, deviceId = deviceId))
            } catch (e: DataIntegrityViolationException) {
                // 동시성 문제로 중복 insert 시도 시 기존 레코드 업데이트
                val existingToken = deviceTokenRepository.findByUserAndDeviceId(user, deviceId)
                    ?: throw e
                existingToken.updateToken(token)
                existingToken
            }
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

    fun deleteByToken(token: String) {
        deviceTokenRepository.deleteByToken(token)
    }
}
