package com.example.falletterbackend.falletter.facade.admin

import com.example.falletterbackend.falletter.entity.admin.Admin
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.example.falletterbackend.falletter.entity.admin.repository.AdminRepository
import com.example.falletterbackend.falletter.exception.admin.AdminNotFoundException
import com.example.falletterbackend.falletter.exception.admin.AlreadyAdminEmailException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AdminFacade(
    private val adminRepository: AdminRepository
) {
    fun getCurrentAdmin(): Admin {
        val email: String = SecurityContextHolder.getContext().authentication.name
        return getByEmail(email)
    }

    fun getByEmail(email: String): Admin {
        return adminRepository.findByEmail(email) ?: throw AdminNotFoundException
    }

    fun getById(id: Long): Admin {
        return adminRepository.findByIdOrNull(id) ?: throw AdminNotFoundException
    }

    fun existsByEmail(email: String): Boolean {
        return adminRepository.existsByEmail(email)
    }

    fun validateEmailNotExists(email: String) {
        if (adminRepository.existsByEmail(email)) {
            throw AlreadyAdminEmailException
        }
    }

    fun save(admin: Admin): Admin {
        return adminRepository.save(admin)
    }

    fun findAllByStatus(status: AdminStatus): List<Admin> {
        return adminRepository.findAllByStatus(status)
    }
}
