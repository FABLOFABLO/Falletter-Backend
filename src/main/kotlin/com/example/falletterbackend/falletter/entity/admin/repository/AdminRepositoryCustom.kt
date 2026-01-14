package com.example.falletterbackend.falletter.entity.admin.repository

import com.example.falletterbackend.falletter.entity.admin.Admin
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus

interface AdminRepositoryCustom {
    fun findByEmail(email: String): Admin?

    fun existsByEmail(email: String): Boolean

    fun findAllByStatus(status: AdminStatus): List<Admin>
}
