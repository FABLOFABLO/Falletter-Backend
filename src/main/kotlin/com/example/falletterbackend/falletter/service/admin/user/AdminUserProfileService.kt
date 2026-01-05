package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserProfileResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserSanctionResponse
import com.example.falletterbackend.falletter.facade.sanction.SanctionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserProfileService(
    private val userFacade: UserFacade,
    private val sanctionFacade: SanctionFacade
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): AdminUserProfileResponse {
        val user = userFacade.getUserById(userId)

        val sanctions = sanctionFacade.getSanctionsByUser(user).map { sanction ->
            AdminUserSanctionResponse(
                id = sanction.id,
                type = sanction.type,
                days = sanction.days,
                reason = sanction.blockReason,
                startDate = sanction.startDate,
                endDate = sanction.endDate,
                createdAt = sanction.createdAt
            )
        }

        val warningCount = sanctionFacade.getWarningCountByUser(user)

        return AdminUserProfileResponse(
            id = user.id,
            schoolNumber = user.schoolNumber,
            name = user.name,
            profileImage = user.profileImage,
            gender = user.gender,
            warningCount = warningCount,
            sanctions = sanctions
        )
    }
}
