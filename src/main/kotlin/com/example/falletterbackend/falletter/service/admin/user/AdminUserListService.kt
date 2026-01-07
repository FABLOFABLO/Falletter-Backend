package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserListResponse
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserListService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade
) {
    @Transactional(readOnly = true)
    fun execute(pageable: Pageable): Page<AdminUserListResponse> {
        return userFacade.getAllUsers(pageable).map { user ->
            AdminUserListResponse(
                id = user.id,
                schoolNumber = user.schoolNumber,
                name = user.name,
                profileImage = user.profileImage,
                gender = user.gender,
                warningCount = suspendFacade.getWarningCountByUser(user)
            )
        }
    }
}
