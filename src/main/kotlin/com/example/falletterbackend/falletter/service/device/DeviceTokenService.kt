package com.example.falletterbackend.falletter.service.device

import com.example.falletterbackend.falletter.dto.device.request.DeviceTokenRequest
import com.example.falletterbackend.falletter.dto.device.response.DeviceTokenRegisterResponse
import com.example.falletterbackend.falletter.facade.device.DeviceTokenFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceTokenService(
    private val userFacade: UserFacade,
    private val deviceTokenFacade: DeviceTokenFacade
) {
    @Transactional
    fun registerToken(request: DeviceTokenRequest): DeviceTokenRegisterResponse {
        val user = userFacade.getCurrentUser()
        val deviceToken = deviceTokenFacade.saveOrUpdate(user, request.token, request.deviceId)

        return DeviceTokenRegisterResponse(id = deviceToken.id)
    }

    @Transactional
    fun deleteToken(deviceId: Long) {
        deviceTokenFacade.deleteById(deviceId)
    }
}
