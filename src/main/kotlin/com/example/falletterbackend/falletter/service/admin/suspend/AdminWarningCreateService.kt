package com.example.falletterbackend.falletter.service.admin.suspend

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminWarningCreateService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade
) {
    @AdminOnly
    @Transactional
    fun execute(userId: Long) {
        val user = userFacade.getUserById(userId)
        suspendFacade.createWarning(user)
    }
}
