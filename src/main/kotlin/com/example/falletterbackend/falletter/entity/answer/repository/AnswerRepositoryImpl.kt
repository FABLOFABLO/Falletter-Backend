package com.example.falletterbackend.falletter.entity.answer.repository

import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.answer.QAnswer.answer
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AnswerRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : AnswerRepositoryCustom {

    override fun findAllByTargetUserIdWithRelations(target: User): List<Answer> {
        return queryFactory
            .selectFrom(answer)
            .join(answer.question).fetchJoin()
            .join(answer.targetUserId).fetchJoin()
            .join(answer.writerId).fetchJoin()
            .where(answer.targetUserId.eq(target))
            .fetch()
    }
}
