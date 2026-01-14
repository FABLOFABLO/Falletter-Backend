package com.example.falletterbackend.common.security.auth

import com.example.falletterbackend.falletter.entity.admin.Admin
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AdminAuthDetails(
    private val admin: Admin
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(admin.role.key))

    override fun getPassword(): String? = null

    override fun getUsername(): String = admin.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    fun getAdmin(): Admin = admin
}
