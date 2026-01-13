package com.example.falletterbackend.falletter.entity.notification.repository

import com.example.falletterbackend.falletter.entity.notification.Notification
import com.example.falletterbackend.falletter.entity.notification.QNotification.notification
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class NotificationRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : NotificationRepositoryCustom {

    override fun findByUser(user: User): Notification? {
        return queryFactory
            .selectFrom(notification)
            .where(notification.user.eq(user))
            .fetchOne()
    }

    override fun existsByUser(user: User): Boolean {
        return queryFactory
            .selectOne()
            .from(notification)
            .where(notification.user.eq(user))
            .fetchFirst() != null
    }
}
