package com.example.falletterbackend.falletter.entity.item.repository

import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.dto.item.response.ItemLetterGetCountResponse
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.item.QItem.item
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : ItemRepositoryCustom {

    override fun findBrickCountByUser(user: User): ItemBrickGetCountResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    ItemBrickGetCountResponse::class.java,
                    item.user.id,
                    item.brickCount
                )
            )
            .from(item)
            .where(item.user.eq(user))
            .fetchOne()
    }

    override fun findLetterCountByUser(user: User): ItemLetterGetCountResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    ItemLetterGetCountResponse::class.java,
                    item.user.id,
                    item.letterCount
                )
            )
            .from(item)
            .where(item.user.eq(user))
            .fetchOne()
    }

    override fun findEntityByUser(user: User): Item? {
        return queryFactory
            .selectFrom(item)
            .where(item.user.eq(user))
            .fetchOne()
    }

    override fun existsByUserAndLetterCountGreaterThan(user: User, count: Long): Boolean {
        return queryFactory
            .selectOne()
            .from(item)
            .where(
                item.user.eq(user),
                item.letterCount.gt(count)
            )
            .fetchFirst() != null
    }
}
