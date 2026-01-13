package com.example.falletterbackend.falletter.entity.hint.repository

import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.hint.QHint.hint
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class HintRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : HintRepositoryCustom {

    override fun findByIdAndUser(id: Long, user: User): HintResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    HintResponse::class.java,
                    hint.id,
                    hint.firstHint,
                    hint.secondHint,
                    hint.thirdHint,
                    hint.user.id
                )
            )
            .from(hint)
            .where(
                hint.id.eq(id),
                hint.user.eq(user)
            )
            .fetchOne()
    }
}
