package com.example.falletterbackend.falletter.entity.admin.repository

import com.example.falletterbackend.falletter.entity.admin.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Long>, AdminRepositoryCustom {
}
