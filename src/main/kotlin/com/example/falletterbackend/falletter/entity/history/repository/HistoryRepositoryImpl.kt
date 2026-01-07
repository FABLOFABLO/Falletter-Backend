package com.example.falletterbackend.falletter.entity.history.repository

import com.example.falletterbackend.falletter.entity.history.History
import com.example.falletterbackend.falletter.entity.history.QHistory.history
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class HistoryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : HistoryRepositoryCustom {

    override fun findAllByWriterUserIdWithRelations(writer: User): List<History> {
        return queryFactory
            .selectFrom(history)
            .leftJoin(history.question).fetchJoin()
            .join(history.targetUserId).fetchJoin()
            .join(history.writerUserId).fetchJoin()
            .where(history.writerUserId.eq(writer))
            .fetch()
    }
}
