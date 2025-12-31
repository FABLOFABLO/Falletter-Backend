package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserProfileResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserBlockResponse
import com.example.falletterbackend.falletter.facade.block.BlockFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserProfileService(
    private val userFacade: UserFacade,
    private val blockFacade: BlockFacade
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): AdminUserProfileResponse {
        val user = userFacade.getUserById(userId)

        val blocks = blockFacade.getBlocksByUser(user).map { block ->
            AdminUserBlockResponse(
                id = block.id,
                type = block.type,
                days = block.days,
                reason = block.blockReason,
                startDate = block.startDate,
                endDate = block.endDate,
                createdAt = block.createdAt
            )
        }

        val warningCount = blockFacade.getWarningCountByUser(user)

        return AdminUserProfileResponse(
            id = user.id,
            schoolNumber = user.schoolNumber,
            name = user.name,
            profileImage = user.profileImage,
            gender = user.gender,
            warningCount = warningCount,
            blocks = blocks
        )
    }
}
