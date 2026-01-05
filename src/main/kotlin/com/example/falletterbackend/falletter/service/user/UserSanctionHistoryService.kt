package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserSanctionHistoryResponse
import com.example.falletterbackend.falletter.facade.sanction.SanctionFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSanctionHistoryService(
    private val userFacade: UserFacade,
    private val sanctionFacade: SanctionFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<UserSanctionHistoryResponse> {
        val user = userFacade.getCurrentUser()
        val sanctions = sanctionFacade.getSanctionsByUser(user)

        return sanctions.map { sanction ->
            UserSanctionHistoryResponse(
                id = sanction.id,
                type = sanction.type,
                days = sanction.days,
                startDate = sanction.startDate,
                endDate = sanction.endDate
            )
        }
    }
}
