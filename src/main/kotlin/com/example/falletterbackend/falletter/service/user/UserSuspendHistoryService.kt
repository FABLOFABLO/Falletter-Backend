package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserSuspendHistoryResponse
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSuspendHistoryService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<UserSuspendHistoryResponse> {
        val user = userFacade.getCurrentUser()
        val suspends = suspendFacade.getSuspendsByUser(user)

        return suspends.map { suspend ->
            UserSuspendHistoryResponse(
                id = suspend.id,
                type = suspend.type,
                days = suspend.days,
                startDate = suspend.startDate,
                endDate = suspend.endDate
            )
        }
    }
}
