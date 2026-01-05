package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserSanctionReasonResponse
import com.example.falletterbackend.falletter.entity.sanction.repository.SanctionRepository
import com.example.falletterbackend.falletter.exception.user.SanctionNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSanctionReasonService(
    private val userFacade: UserFacade,
    private val sanctionRepository: SanctionRepository
) {
    @Transactional(readOnly = true)
    fun execute(suspendId: Long): UserSanctionReasonResponse {
        val user = userFacade.getCurrentUser()
        val sanction = sanctionRepository.findById(suspendId)
            .orElseThrow { SanctionNotFoundException }

        if (sanction.user.id != user.id) {
            throw SanctionNotFoundException
        }

        return UserSanctionReasonResponse(
            id = sanction.id,
            reason = sanction.blockReason
        )
    }
}
