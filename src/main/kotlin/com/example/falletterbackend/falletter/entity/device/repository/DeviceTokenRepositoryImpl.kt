package com.example.falletterbackend.falletter.entity.device.repository

import com.example.falletterbackend.falletter.entity.device.DeviceToken
import com.example.falletterbackend.falletter.entity.device.QDeviceToken.deviceToken
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class DeviceTokenRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : DeviceTokenRepositoryCustom {

    override fun findAllByUser(user: User): List<DeviceToken> {
        return queryFactory
            .selectFrom(deviceToken)
            .where(deviceToken.user.eq(user))
            .fetch()
    }

    override fun findByUserAndDeviceId(user: User, deviceId: String): DeviceToken? {
        return queryFactory
            .selectFrom(deviceToken)
            .where(
                deviceToken.user.eq(user),
                deviceToken.deviceId.eq(deviceId)
            )
            .fetchOne()
    }

    override fun deleteByUserAndDeviceId(user: User, deviceId: String): Long {
        return queryFactory
            .delete(deviceToken)
            .where(
                deviceToken.user.eq(user),
                deviceToken.deviceId.eq(deviceId)
            )
            .execute()
    }

    override fun deleteAllByUser(user: User): Long {
        return queryFactory
            .delete(deviceToken)
            .where(deviceToken.user.eq(user))
            .execute()
    }

    override fun deleteByToken(token: String): Long {
        return queryFactory
            .delete(deviceToken)
            .where(deviceToken.token.eq(token))
            .execute()
    }
}
