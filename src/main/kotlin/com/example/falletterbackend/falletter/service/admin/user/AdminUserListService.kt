package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserListResponse
import com.example.falletterbackend.falletter.facade.block.BlockFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserListService(
    private val userFacade: UserFacade,
    private val blockFacade: BlockFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<AdminUserListResponse> {
        return userFacade.getAllUsers().map { user ->
            AdminUserListResponse(
                id = user.id,
                schoolNumber = user.schoolNumber,
                name = user.name,
                profileImage = user.profileImage,
                gender = user.gender,
                warningCount = blockFacade.getWarningCountByUser(user)
            )
        }
    }
}
