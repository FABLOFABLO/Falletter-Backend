package com.example.falletterbackend.falletter.entity.community.repository

import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.community.QCommunity.community
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CommunityRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CommunityRepositoryCustom {

    override fun findAllWithAuthorOrderByIdDesc(): List<Community> {
        return queryFactory
            .selectFrom(community)
            .join(community.author).fetchJoin()
            .orderBy(community.id.desc())
            .fetch()
    }
}
