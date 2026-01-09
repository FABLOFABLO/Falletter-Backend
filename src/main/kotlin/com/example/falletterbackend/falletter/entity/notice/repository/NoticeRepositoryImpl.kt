package com.example.falletterbackend.falletter.entity.notice.repository

import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.entity.notice.QNotice.notice
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class NoticeRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : NoticeRepositoryCustom {

    override fun findAllWithAuthor(): List<Notice> {
        return queryFactory
            .selectFrom(notice)
            .join(notice.author).fetchJoin()
            .orderBy(notice.createdAt.desc())
            .fetch()
    }
}
