package com.example.falletterbackend.falletter.entity.question.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QuestionRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QuestionRepositoryCustom {
}
