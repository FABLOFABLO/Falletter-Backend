package com.example.falletterbackend.falletter.entity.suspend.repository

import com.example.falletterbackend.falletter.entity.suspend.QSuspend.suspend
import com.example.falletterbackend.falletter.entity.suspend.enums.SuspendType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SuspendRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : SuspendRepositoryCustom {

    override fun countWarningsByUserIds(userIds: List<Long>, type: SuspendType): Map<Long, Long> {
        if (userIds.isEmpty()) return emptyMap()

        val results = queryFactory
            .select(suspend.user.id, suspend.count())
            .from(suspend)
            .where(
                suspend.user.id.`in`(userIds),
                suspend.type.eq(type)
            )
            .groupBy(suspend.user.id)
            .fetch()

        return results.associate { tuple ->
            tuple.get(suspend.user.id)!! to tuple.get(suspend.count())!!
        }
    }
}
