package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserListResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserProfileResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserSuspendResponse
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade
) {
    @Transactional(readOnly = true)
    fun getUserList(pageable: Pageable): Page<AdminUserListResponse> {
        val users = userFacade.getAllUsers(pageable)
        val userIds = users.content.map { it.id }
        val warningCounts = suspendFacade.getWarningCountsByUserIds(userIds)

        return users.map { user ->
            AdminUserListResponse.from(user, warningCounts[user.id] ?: 0L)
        }
    }

    @Transactional(readOnly = true)
    fun getUserProfile(userId: Long): AdminUserProfileResponse {
        val user = userFacade.getUserById(userId)
        val suspends = suspendFacade.getSuspendsByUser(user).map { AdminUserSuspendResponse.from(it) }
        val warningCount = suspendFacade.getWarningCountByUser(user)

        return AdminUserProfileResponse.from(user, warningCount, suspends)
    }
}
