package com.example.falletterbackend.falletter.entity.admin.repository

import com.example.falletterbackend.falletter.entity.admin.Admin
import com.example.falletterbackend.falletter.entity.admin.QAdmin.admin
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AdminRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : AdminRepositoryCustom {

    override fun findByEmail(email: String): Admin? {
        return queryFactory
            .selectFrom(admin)
            .where(admin.email.eq(email))
            .fetchOne()
    }

    override fun existsByEmail(email: String): Boolean {
        return queryFactory
            .selectOne()
            .from(admin)
            .where(admin.email.eq(email))
            .fetchFirst() != null
    }

    override fun findAllByStatus(status: AdminStatus): List<Admin> {
        return queryFactory
            .selectFrom(admin)
            .where(admin.status.eq(status))
            .orderBy(admin.createdAt.desc())
            .fetch()
    }
}
