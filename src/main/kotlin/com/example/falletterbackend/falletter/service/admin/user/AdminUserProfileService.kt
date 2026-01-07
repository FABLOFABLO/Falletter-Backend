package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserProfileResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserSuspendResponse
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserProfileService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): AdminUserProfileResponse {
        val user = userFacade.getUserById(userId)

        val suspends = suspendFacade.getSuspendsByUser(user).map { suspend ->
            AdminUserSuspendResponse(
                id = suspend.id,
                type = suspend.type,
                days = suspend.days,
                reason = suspend.blockReason,
                startDate = suspend.startDate,
                endDate = suspend.endDate,
                createdAt = suspend.createdAt
            )
        }

        val warningCount = suspendFacade.getWarningCountByUser(user)

        return AdminUserProfileResponse(
            id = user.id,
            schoolNumber = user.schoolNumber,
            name = user.name,
            profileImage = user.profileImage,
            gender = user.gender,
            warningCount = warningCount,
            suspends = suspends
        )
    }
}
