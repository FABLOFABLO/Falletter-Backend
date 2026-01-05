package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.request.AdminUserSanctionRequest
import com.example.falletterbackend.falletter.facade.sanction.SanctionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserSanctionService(
    private val userFacade: UserFacade,
    private val sanctionFacade: SanctionFacade
) {
    @Transactional
    fun execute(userId: Long, request: AdminUserSanctionRequest) {
        val user = userFacade.getUserById(userId)
        sanctionFacade.createBlock(user, request.days, request.reason)
    }
}
