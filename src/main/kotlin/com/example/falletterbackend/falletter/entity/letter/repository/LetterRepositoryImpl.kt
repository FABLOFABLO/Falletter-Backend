package com.example.falletterbackend.falletter.entity.letter.repository

import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.QLetter.letter
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class LetterRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : LetterRepositoryCustom {

    override fun findAllUnpassedWithRelations(): List<Letter> {
        return queryFactory
            .selectFrom(letter)
            .join(letter.reception).fetchJoin()
            .join(letter.sender).fetchJoin()
            .where(letter.isPassed.eq(false))
            .fetch()
    }

    override fun findAllByReceptionIdAndPassedWithSender(receiverId: Long): List<Letter> {
        return queryFactory
            .selectFrom(letter)
            .join(letter.sender).fetchJoin()
            .where(
                letter.reception.id.eq(receiverId),
                letter.isPassed.eq(true)
            )
            .fetch()
    }

    override fun findAllBySenderAndPassedWithReception(sender: User): List<Letter> {
        return queryFactory
            .selectFrom(letter)
            .join(letter.reception).fetchJoin()
            .where(
                letter.sender.eq(sender),
                letter.isPassed.eq(true)
            )
            .fetch()
    }

    override fun findAllUndeliveredBefore(threshold: LocalDateTime): List<Letter> {
        return queryFactory
            .selectFrom(letter)
            .where(
                letter.isDelivered.eq(false),
                letter.createdAt.loe(threshold)
            )
            .fetch()
    }
}
