package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.facade.sanction.SanctionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserWarningService(
    private val userFacade: UserFacade,
    private val sanctionFacade: SanctionFacade
) {
    @AdminOnly
    @Transactional
    fun execute(userId: Long) {
        val user = userFacade.getUserById(userId)
        sanctionFacade.createWarning(user)
    }
}
