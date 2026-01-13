package com.example.falletterbackend.falletter.entity.comment.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CommentRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CommentRepositoryCustom {
}
