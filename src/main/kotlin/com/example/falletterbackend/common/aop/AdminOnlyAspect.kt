package com.example.falletterbackend.common.aop

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.example.falletterbackend.falletter.exception.auth.AccessDeniedException
import com.example.falletterbackend.falletter.facade.admin.AdminFacade
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class AdminOnlyAspect(
    private val adminFacade: AdminFacade
) {
    @Before("@annotation(adminOnly)")
    fun checkAdminRole(adminOnly: AdminOnly) {
        val admin = adminFacade.getCurrentAdmin()
        if (admin.status != AdminStatus.APPROVED) {
            throw AccessDeniedException
        }
    }
}
