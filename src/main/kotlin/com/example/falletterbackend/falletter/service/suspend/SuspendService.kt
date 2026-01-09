package com.example.falletterbackend.falletter.service.suspend

import com.example.falletterbackend.falletter.dto.user.response.UserSuspendHistoryResponse
import com.example.falletterbackend.falletter.dto.user.response.UserSuspendReasonResponse
import com.example.falletterbackend.falletter.entity.suspend.repository.SuspendRepository
import com.example.falletterbackend.falletter.exception.user.SuspendNotFoundException
import com.example.falletterbackend.falletter.facade.suspend.SuspendFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SuspendService(
    private val userFacade: UserFacade,
    private val suspendFacade: SuspendFacade,
    private val suspendRepository: SuspendRepository
) {
    @Transactional(readOnly = true)
    fun getSuspendHistory(): List<UserSuspendHistoryResponse> {
        val user = userFacade.getCurrentUser()
        val suspends = suspendFacade.getSuspendsByUser(user)

        return suspends.map { UserSuspendHistoryResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getSuspendReason(suspendId: Long): UserSuspendReasonResponse {
        val user = userFacade.getCurrentUser()
        val suspend = suspendRepository.findById(suspendId)
            .orElseThrow { SuspendNotFoundException }

        if (suspend.user.id != user.id) {
            throw SuspendNotFoundException
        }

        return UserSuspendReasonResponse.from(suspend)
    }
}
