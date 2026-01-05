package com.example.falletterbackend.falletter.service.device

import com.example.falletterbackend.falletter.facade.device.DeviceTokenFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceTokenDeleteService(
    private val deviceTokenFacade: DeviceTokenFacade
) {
    @Transactional
    fun execute(deviceId: Long) {
        deviceTokenFacade.deleteById(deviceId)
    }
}
