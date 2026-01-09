package com.example.falletterbackend.falletter.service.admin.suspend

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.dto.admin.user.request.AdminUserSuspendRequest
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminSuspendService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade
) {
    @AdminOnly
    @Transactional
    fun createWarning(userId: Long) {
        val user = userFacade.getUserById(userId)
        suspendFacade.createWarning(user)
    }

    @AdminOnly
    @Transactional
    fun createBlock(userId: Long, request: AdminUserSuspendRequest) {
        val user = userFacade.getUserById(userId)
        suspendFacade.createBlock(user, request.days, request.reason)
    }
}
