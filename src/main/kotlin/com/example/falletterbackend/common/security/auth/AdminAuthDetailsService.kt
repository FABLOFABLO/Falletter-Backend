package com.example.falletterbackend.common.security.auth

import com.example.falletterbackend.falletter.facade.admin.AdminFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AdminAuthDetailsService(
    private val adminFacade: AdminFacade
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val admin = adminFacade.getByEmail(email)
        return AdminAuthDetails(admin)
    }
}
