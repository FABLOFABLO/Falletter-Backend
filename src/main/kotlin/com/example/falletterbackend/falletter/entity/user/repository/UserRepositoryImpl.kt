package com.example.falletterbackend.falletter.entity.user.repository

import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.entity.user.QUser.user
import com.example.falletterbackend.falletter.entity.user.User
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : UserRepositoryCustom {

    override fun findByEmail(email: String): User? {
        return queryFactory
            .selectFrom(user)
            .where(user.email.eq(email))
            .fetchOne()
    }

    override fun existsByEmail(email: String): Boolean {
        return queryFactory
            .selectOne()
            .from(user)
            .where(user.email.eq(email))
            .fetchFirst() != null
    }

    override fun existsBySchoolNumber(schoolNumber: String): Boolean {
        return queryFactory
            .selectOne()
            .from(user)
            .where(user.schoolNumber.eq(schoolNumber))
            .fetchFirst() != null
    }

    override fun findAllStudents(): List<UserGetAllStudentResponse> {
        return queryFactory
            .select(
                Projections.constructor(
                    UserGetAllStudentResponse::class.java,
                    user.id,
                    user.schoolNumber,
                    user.name
                )
            )
            .from(user)
            .fetch()
    }
}
