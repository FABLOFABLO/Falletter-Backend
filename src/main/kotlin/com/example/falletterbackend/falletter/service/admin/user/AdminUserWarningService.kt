package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.facade.block.BlockFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserWarningService(
    private val userFacade: UserFacade,
    private val blockFacade: BlockFacade
) {
    @Transactional
    fun execute(userId: Long) {
        val user = userFacade.getUserById(userId)
        blockFacade.createWarning(user)
    }
}
