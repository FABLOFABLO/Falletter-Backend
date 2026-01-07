package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserSuspendReasonResponse
import com.example.falletterbackend.falletter.entity.suspend.repository.SuspendRepository
import com.example.falletterbackend.falletter.exception.user.SuspendNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSuspendReasonService(
    private val userFacade: UserFacade,
    private val suspendRepository: SuspendRepository
) {
    @Transactional(readOnly = true)
    fun execute(suspendId: Long): UserSuspendReasonResponse {
        val user = userFacade.getCurrentUser()
        val suspend = suspendRepository.findById(suspendId)
            .orElseThrow { SuspendNotFoundException }

        if (suspend.user.id != user.id) {
            throw SuspendNotFoundException
        }

        return UserSuspendReasonResponse(
            id = suspend.id,
            reason = suspend.blockReason
        )
    }
}
