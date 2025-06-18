package com.example.falletterbackend.common.security

import com.example.falletterbackend.falletter.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(accountId: String): UserDetails {
        val user = userFacade.getByAccountId(accountId)
        return AuthDetails(user)
    }
}