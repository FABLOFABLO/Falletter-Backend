package com.example.falletterbackend.falletter.entity.device

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_device_token")
class DeviceToken(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "token", columnDefinition = "VARCHAR(512)", nullable = false)
    var token: String,

    @Column(name = "device_id", columnDefinition = "VARCHAR(255)", nullable = false)
    val deviceId: String
) : EntityBase() {
    fun updateToken(newToken: String) {
        this.token = newToken
    }
}
