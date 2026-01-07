package com.example.falletterbackend.common.aop

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.exception.auth.AccessDeniedException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class AdminOnlyAspect(
    private val userFacade: UserFacade
) {
    @Before("@annotation(adminOnly)")
    fun checkAdminRole(adminOnly: AdminOnly) {
        val user = userFacade.getCurrentUser()
        if (user.role != Role.ADMIN) {
            throw AccessDeniedException
        }
    }
}
