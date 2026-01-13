package com.example.falletterbackend.falletter.entity.device.repository

import com.example.falletterbackend.falletter.entity.device.DeviceToken
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceTokenRepository : JpaRepository<DeviceToken, Long>, DeviceTokenRepositoryCustom {
}
